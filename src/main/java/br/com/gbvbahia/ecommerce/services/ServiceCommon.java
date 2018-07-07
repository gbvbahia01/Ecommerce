package br.com.gbvbahia.ecommerce.services;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.util.StringHelper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 31/05/18
 */
public abstract class ServiceCommon<DTO, ENT, ID, R extends JpaRepository<ENT, ID>> implements ServiceContract<DTO, ID> {

    private final ParameterService parameterService;
    private final Mapper mapper;
    private final Class<DTO> clazz;

    //============
    // Constructor
    //============
    public ServiceCommon(ParameterService parameterService,
                         Mapper mapper,
                         Class<DTO> clazz) {

        this.parameterService = parameterService;
        this.mapper = mapper;
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
     *
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
        entities.parallelStream().forEach(e -> dtosList.add(convert(e, 0)));
        return Collections.unmodifiableList(dtosList);
    }


    /**
     *
     * @param entity
     * @param counter will be removed.
     * @return
     */
    private DTO convert(ENT entity, int counter) {
        if (entity == null) {
            return null;
        }
        logger.debug("DOZER: Converting {} to {}", entity.toString(), clazz.getSimpleName());
        try {
            return getMapper().map(entity, clazz);
        } catch (NullPointerException e) {
            //TODO This need a fix as soon as possible. Dozer has a intermittent null pointer exception.
            logger.error("DOZER Error-{}: Converting {} to {}", counter, entity.toString(), clazz.getSimpleName(), e);
            return convert(entity, counter + 1);
        }
    }

    protected DTO convert(ENT entity) {
        return convert(entity, 0);
    }

    protected <TO, FOR> List<FOR> convert(List<TO> entitiesTo, Class<FOR> entitiesFor) {

            List<FOR> dtosList = new ArrayList<>(entitiesTo.size());
            entitiesTo.parallelStream().forEach(entity -> {
                try {
                dtosList.add(convert(entity, entitiesFor.newInstance()));
                } catch (InstantiationException | IllegalAccessException ex) {
                    logger.error("Error creating new instance", ex);
                }
            });
            return Collections.unmodifiableList(dtosList);
    }


    protected <TO, FOR> FOR convert(TO entityTo, FOR entityFor) {
        if (entityTo == null) {
            return null;
        }
        logger.debug("DOZER: Converting {} to {}",
                     entityTo.toString(),
                     entityFor.getClass().getSimpleName());
        try {
            getMapper().map(entityTo, entityFor);
            return entityFor;
        } catch (NullPointerException ex) {
            //TODO This need a fix as soon as possible. Dozer has a intermittent null pointer exception.
            logger.error("DOZER Error: Converting {} to {}",
                         entityTo.toString(),
                         entityFor.getClass().getSimpleName(),
                         ex);
            return convert(entityTo, entityFor);
        }
    }

    protected ParameterService getParameterService() {
        return this.parameterService;
    }

    protected Mapper getMapper() {
        return this.mapper;
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
        return mapper.map(opt.get(), clazz);
    }
}
