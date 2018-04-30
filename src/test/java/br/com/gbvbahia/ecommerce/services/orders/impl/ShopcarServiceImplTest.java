package br.com.gbvbahia.ecommerce.services.orders.impl;

import br.com.gbvbahia.ecommerce.model.entity.customers.Customer;
import br.com.gbvbahia.ecommerce.model.entity.orders.Shopcar;
import br.com.gbvbahia.ecommerce.repositories.orders.ShopcarRepository;
import br.com.gbvbahia.ecommerce.services.orders.ShopcarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

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


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shopcarService = new ShopcarServiceImpl(shopcarRepository);
    }

    @Test
    public void testFindBySerial_Null() {
        Shopcar shopcar = shopcarService.findBySerial(null);
        Assert.assertNull(shopcar);
    }

    @Test
    public void testFindBySerial_NotNull() {
        String serialArg = "serial";

        Shopcar shopcarExpected = new Shopcar();
        shopcarExpected.setId(100L);

        Mockito.when(shopcarRepository.findBySerialUniqueId(serialArg)).thenReturn(shopcarExpected);

        Shopcar shopcar = shopcarService.findBySerial(serialArg);
        Assert.assertNotNull(shopcar);
        Assert.assertEquals(shopcarExpected, shopcar);
    }

    @Test
    public void testTindByCustomer_Null() {
        Customer customer = null;
        Shopcar shopcar = shopcarService.findByCustomer(customer);
        Assert.assertNull(shopcar);
    }

    @Test
    public void testTindByCustomer_NotNull() {
        Customer customer = new Customer();
        customer.setId(10L);

        Shopcar shopcarExpected = new Shopcar();
        shopcarExpected.setId(100L);

        Mockito.when(shopcarRepository.findByCustomer(customer)).thenReturn(shopcarExpected);

        Shopcar shopcar = shopcarService.findByCustomer(customer);
        Assert.assertNotNull(shopcar);
        Assert.assertEquals(shopcarExpected, shopcar);
    }
}