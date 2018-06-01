package br.com.gbvbahia.ecommerce;

import br.com.gbvbahia.ecommerce.mappers.MapperBuilder;
import org.dozer.Mapper;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 31/05/18
 */
public class DozerMapperBuilderTestImpl implements MapperBuilder {

    @Override
    public Mapper getMapper() {
        return TestFactory.getDozerForUnitTest();
    }
}
