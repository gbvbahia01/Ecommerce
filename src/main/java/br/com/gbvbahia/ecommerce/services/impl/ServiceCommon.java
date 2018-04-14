package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.util.StringHelper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

public abstract class ServiceCommon<T, ID, R extends CrudRepository<T, ID>> implements ServiceContract<T, ID> {

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
    
    //===================
    // Repository Methods
    //===================
    public Optional<T> findBydId(ID id) {
        return getRepository().findById(id);
    }
}
