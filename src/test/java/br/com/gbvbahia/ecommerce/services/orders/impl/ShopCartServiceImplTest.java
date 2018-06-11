package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.ShopCart;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopCartRepository;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.orders.ShopCartDTO;
import br.com.gbvbahia.ecommerce.services.orders.ShopCartService;
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
public class ShopCartServiceImplTest {

    private ShopCartService shopCartService;

    @Mock
    private ShopCartRepository shopCartRepository;

    @Mock
    private ParameterService parameterService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shopCartService = new ShopCartServiceImpl(parameterService,
                                                  shopCartRepository,
                                                  TestFactory.getDozerForUnitTest());

    }

    @Test
    public void testFindBySerial_Null() {
        ShopCartDTO shopCart = shopCartService.findBySerial(null);
        Assert.assertNull(shopCart);
    }

    @Test
    public void testFindBySerial_NotNull() throws Exception {
        String serialArg = "serial";

        ShopCart shopCartExpected = new ShopCart();
        shopCartExpected.setId(100L);

        Mockito.when(shopCartRepository.findBySerialUniqueId(serialArg)).thenReturn(shopCartExpected);

        ShopCartDTO shopCart = shopCartService.findBySerial(serialArg);
        Assert.assertNotNull(shopCart);
        Assert.assertEquals(shopCartExpected.getId(), shopCart.getId());
    }

    @Test
    public void testTindByCustomer_Null() {
        Customer customer = null;
        ShopCartDTO shopCart = shopCartService.findByCustomer(customer);
        Assert.assertNull(shopCart);
    }

    @Test
    public void testTindByCustomer_NotNull() throws Exception {
        Customer customer = new Customer();
        customer.setId(10L);

        ShopCart shopCartExpected = new ShopCart();
        shopCartExpected.setId(100L);

        Mockito.when(shopCartRepository.findByCustomer(customer)).thenReturn(shopCartExpected);

        ShopCartDTO shopCart = shopCartService.findByCustomer(customer);
        Assert.assertNotNull(shopCart);
        Assert.assertEquals(shopCartExpected.getId(), shopCart.getId());
    }
}