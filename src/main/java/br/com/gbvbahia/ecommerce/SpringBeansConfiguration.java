package br.com.gbvbahia.ecommerce;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;


/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 13/05/18
 */
@Configuration
public class SpringBeansConfiguration {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Bean(name = "org.dozer.Mapper")
    @Scope("singleton")
    public Mapper dozerBean() {
        List<String> mappingFiles = Arrays.asList(
                "dozer/dozerBeanMapping.xml"
        );
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}
