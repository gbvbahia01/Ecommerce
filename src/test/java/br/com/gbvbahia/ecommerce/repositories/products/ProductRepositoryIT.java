package br.com.gbvbahia.ecommerce.repositories.products;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void testLiquibaseRunning() {
        Long products = productRepository.count();

        Assert.assertTrue("Liquibase is not working, no products founded.", products > 0);
    }
}