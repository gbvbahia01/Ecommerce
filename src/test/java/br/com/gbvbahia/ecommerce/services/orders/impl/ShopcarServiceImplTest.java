package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.services.dto.orders.ShopcarDTO;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopcarRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.orders.ShopcarService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
public class ShopcarServiceImplTest {

    private ShopcarService shopcarService;

    @Mock
    private ShopcarRepository shopcarRepository;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DozerBeanMapper dozer;

    private Mapper mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shopcarService = new ShopcarServiceImpl(parameterService,
                                                dozer,
                                                shopcarRepository);

        mapper = TestFactory.getDozerForUnitTest();
    }

    @Test
    public void testFindBySerial_Null() {
        ShopcarDTO shopcar = shopcarService.findBySerial(null);
        Assert.assertNull(shopcar);
    }

    @Test
    public void testFindBySerial_NotNull() {
        String serialArg = "serial";

        Shopcar shopcarExpected = new Shopcar();
        shopcarExpected.setId(100L);

        Mockito.when(shopcarRepository.findBySerialUniqueId(serialArg)).thenReturn(shopcarExpected);
        Mockito.when(dozer.map(shopcarExpected, ShopcarDTO.class)).thenReturn(mapper.map(shopcarExpected, ShopcarDTO.class));

        ShopcarDTO shopcar = shopcarService.findBySerial(serialArg);
        Assert.assertNotNull(shopcar);
        Assert.assertEquals(shopcarExpected.getId(), shopcar.getId());
    }

    @Test
    public void testTindByCustomer_Null() {
        Customer customer = null;
        ShopcarDTO shopcar = shopcarService.findByCustomer(customer);
        Assert.assertNull(shopcar);
    }

    @Test
    public void testTindByCustomer_NotNull() {
        Customer customer = new Customer();
        customer.setId(10L);

        Shopcar shopcarExpected = new Shopcar();
        shopcarExpected.setId(100L);

        Mockito.when(shopcarRepository.findByCustomer(customer)).thenReturn(shopcarExpected);
        Mockito.when(dozer.map(shopcarExpected, ShopcarDTO.class)).thenReturn(mapper.map(shopcarExpected, ShopcarDTO.class));

        ShopcarDTO shopcar = shopcarService.findByCustomer(customer);
        Assert.assertNotNull(shopcar);
        Assert.assertEquals(shopcarExpected.getId(), shopcar.getId());
    }
}