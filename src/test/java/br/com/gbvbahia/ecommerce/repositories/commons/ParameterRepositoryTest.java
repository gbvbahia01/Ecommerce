package br.com.gbvbahia.ecommerce.repositories.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ParameterRepositoryTest {

    @Autowired
    private ParameterRepository parameterRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testExistParameters() {
        Assert.assertFalse(parameterRepository.findAll().isEmpty());
    }
}
