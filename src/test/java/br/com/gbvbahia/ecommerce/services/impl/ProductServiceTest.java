package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.services.ProductService;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productRepository);
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
