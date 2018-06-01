package br.com.gbvbahia.ecommerce.mappers;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 31/05/18
 */
public class DozerMapperBuilderImpl implements MapperBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DozerMapperBuilderImpl.class);

    private static DozerMapperBuilderImpl instance;

    private DozerBeanMapper dozerBean;

    private DozerMapperBuilderImpl() {
    }

    public static synchronized DozerMapperBuilderImpl getInstance() {
        if (instance == null) {
            instance = new DozerMapperBuilderImpl().init();
        }
        return instance;
    }

    private DozerMapperBuilderImpl init() {
        logger.info("Creating dozer mapping file.");
        dozerBean = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
        logger.info("Dozer mapping file created.");
        return this;
    }

    @Override
    public DozerBeanMapper getMapper() {
        return dozerBean;
    }

}
