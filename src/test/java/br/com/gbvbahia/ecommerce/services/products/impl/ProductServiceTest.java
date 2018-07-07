package br.com.gbvbahia.ecommerce.services.products.impl;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.exceptions.NotFoundException;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductStock;
import br.com.gbvbahia.ecommerce.repositories.products.ProductRepository;
import br.com.gbvbahia.ecommerce.repositories.products.ProductStockRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductStockDTO;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductStockRepository productStockRepository;

    @Mock
    private ParameterService parameterService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(parameterService,
                                                productRepository,
                                                productStockRepository,
                                                TestFactory.getDozerForUnitTest());
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

    @Test(expected = NotFoundException.class)
    public void testFindByProductStockId_argNull() {
        productService.findByProductStockId(null);
    }

    @Test(expected = NotFoundException.class)
    public void testFindByProductStockId_optionalIsNotPresent() {
        Optional<ProductStock> optional = Optional.empty();
        Long idPs = 1L;

        Mockito.when(productStockRepository.findById(idPs)).thenReturn(optional);

        productService.findByProductStockId(idPs);
    }

    @Test
    public void testFindByProductStockId() {
        ProductStock ps = TestFactory.makeProductStock(5);
        Assert.assertNotNull(ps);
        Optional<ProductStock> optional = Optional.of(ps);
        Mockito.when(productStockRepository.findById(ps.getId())).thenReturn(optional);

        ProductStockDTO dto = productService.findByProductStockId(ps.getId());
        Assert.assertNotNull(dto);

        compareProductStock(ps, dto);
    }

    private void compareProductStock(ProductStock ps, ProductStockDTO dto) {
        Assert.assertEquals(ps.getId(), dto.getId());
        Assert.assertEquals(ps.getProduct().getId(), dto.getProduct().getId());
        Assert.assertEquals(ps.getProduct().getDescription(), dto.getProduct().getDescription());
        Assert.assertEquals(ps.getProduct().getName(), dto.getProduct().getName());
        Assert.assertEquals(ps.getProduct().getBranch(), dto.getProduct().getBranch());
        Assert.assertEquals(ps.getProduct().getPrice(), dto.getProduct().getPrice());
        Assert.assertEquals(ps.getSpecificationsString(), dto.getSpecificationsString());
    }

    @Test
    public void testListProductStockId() throws Exception {
        List<Long> ids = Arrays.asList(new Long[]{1L, 2L, 3L});
        List<ProductStock> productStocks = new ArrayList<>();
        productStocks.add(TestFactory.makeProductStock(1));
        productStocks.add(TestFactory.makeProductStock(2));
        productStocks.add(TestFactory.makeProductStock(3));

        Mockito.when(productStockRepository.findAllById(ids)).thenReturn(productStocks);

        List<ProductStockDTO> dtos = productService.listProductStockId(ids);

        Assert.assertFalse(dtos.isEmpty());

        productStocks.forEach(ps -> {
            boolean found = false;
            for (ProductStockDTO dto : dtos) {
                if (ps.getId().equals(dto.getId())) {
                    found = true;
                    compareProductStock(ps, dto);
                }
            }
            if (!found) {
                Assert.fail("Not found ProductStock id:" + ps.getId());
            }
        });
    }

}
