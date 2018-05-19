package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DozerBeanMapper dozer;

    private Mapper mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(parameterService, dozer, productRepository);

        mapper = DozerBeanMapperSingletonWrapper.getInstance();
    }

    @Test
    public void testLikeParameterSerach() throws Exception {
        String parameterSearch = "NõtWêllLîkéPÃramèter";
        String expectedParameter = "%notwelllikeparameter%";

        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);

        productService.search(parameterSearch);

            Mockito.verify(productRepository, Mockito.times(1)).searchClean(arg1Captor.capture(),
                                                                                                arg2Captor.capture());

        Assert.assertEquals(expectedParameter, arg1Captor.getValue());
        Assert.assertEquals(expectedParameter, arg2Captor.getValue());

    }

}
