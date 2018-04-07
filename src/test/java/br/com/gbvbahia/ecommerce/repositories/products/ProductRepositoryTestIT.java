package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTestIT {

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

    @Test
    public void testSearchNameClean() {
        String nameCleanParameter = "dourado";

        StringBuilder clean = new StringBuilder("%");
        clean.append(StringUtils.lowerCase(nameCleanParameter));
        clean.append("%");

        int expectedAmount = 4;

        List<Product> searchResult = productRepository.searchClean(clean.toString(), clean.toString());

        Assert.assertNotNull("Search result cannot be null", searchResult);
        Assert.assertFalse("Search result cannot be empty", searchResult.isEmpty());
        Assert.assertEquals("Search result must have " + expectedAmount + " products", expectedAmount, searchResult.size());
    }
}