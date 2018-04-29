package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 29/04/18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testListAllForMenu() {
        List<Category> categories = categoryRepository.listAllForMenu(9);
        Assert.assertFalse(categories.isEmpty());

        int[] priorities = new int[categories.size()];
        for (int idx = 0; idx < categories.size(); idx++) {
            Category category = categories.get(idx);
            priorities[idx] = category.getPriority();
            for (int priority : priorities) {
                Assert.assertTrue(priority <= category.getPriority());
            }
        }
    }

    @Test
    public void testListAllForMenuEmpty() {
        List<Category> categories = categoryRepository.listAllForMenu(1000);
        Assert.assertTrue(categories.isEmpty());
    }

}