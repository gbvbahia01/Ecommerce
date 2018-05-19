package br.com.gbvbahia.ecommerce;

import org.dozer.DozerBeanMapper;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.ContextLoader;

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

    @Bean(name = "org.dozer.Mapper")
    @Scope("singleton")
    public DozerBeanMapper dozerBean() {
        List<String> mappingFiles = Arrays.asList(
                "dozer/dozerBeanMapping.xml"
        );
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }

    /*
    public static <T> T getBean(Class<T> beanClass) {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean(beanClass);
    }
    */
}
