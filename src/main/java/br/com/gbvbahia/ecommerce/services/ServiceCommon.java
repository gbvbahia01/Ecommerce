package br.com.gbvbahia.ecommerce.services;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.util.StringHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class ServiceCommon<DTO, ENT, ID, R extends JpaRepository<ENT, ID>> implements ServiceContract<DTO, ID> {

    private final ParameterService parameterService;
    private final DozerBeanMapper dozer;
    private final Class<DTO> clazz;

    //============
    // Constructor
    //============
    public ServiceCommon(ParameterService parameterService,
                         DozerBeanMapper dozer,
                         Class<DTO> clazz) {

        this.parameterService = parameterService;
        this.dozer = dozer;
        this.clazz = clazz;
    }

    //=========
    // Contract
    //=========
    protected abstract R getRepository();
    
    //========
    // Helpers
    //========
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected enum Like {
        INIT, END, BETWEEN;
    }
     
    /**
     * Put % at init and at the end of String.
     * @param parameter to put between %%.
     * @return %parameter%.
     */
    protected StringBuilder stringCleanForLike(Like likeDef, String parameter) {

        StringBuilder builder = new StringBuilder();

        if (Like.INIT.equals(likeDef) || Like.BETWEEN.equals(likeDef)) {
            builder.append("%");
        }

        builder.append(StringHelper.cleanToSearch(parameter));

        if (Like.END.equals(likeDef) || Like.BETWEEN.equals(likeDef)) {
            builder.append("%");
        }
        return builder;
    }

    protected List<DTO> convert(List<ENT> entities) {
        List<DTO> dtosList = new ArrayList<>(entities.size());
        entities.parallelStream().forEach(e -> dtosList.add(convert(e)));
        return Collections.unmodifiableList(dtosList);
    }

    protected DTO convert(ENT entity) {
        if (entity == null) {
            return null;
        }
        logger.debug("DOZER: Converting {} to {}", entity.toString(), clazz.getSimpleName());
        return getDozer().map(entity, clazz);
    }

    protected ParameterService getParameterService() {
        return  this.parameterService;
    }

    protected DozerBeanMapper getDozer() {
        return this.dozer;
    }

    //===================
    // Repository Methods
    //===================
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        Optional<ENT> opt = getRepository().findById(id);
        if (!opt.isPresent()) {
            return null;
        }
        return dozer.map(opt.get(), clazz);
    }
}
