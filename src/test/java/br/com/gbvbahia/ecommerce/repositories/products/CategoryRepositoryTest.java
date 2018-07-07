package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.model.entity.products.SubCategory;
import br.com.gbvbahia.ecommerce.model.enums.Generous;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
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

    /**
     * Check if unlimited XOR is working.
     */
    @Test
    public void testListAllForMenu_Unlimited() {
        Pageable pageable = PageRequest.of(0, 50);
        List<Category> categories = categoryRepository.listAllForMenu(1000,
                                                                      "unlimited",
                                                                       pageable);
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

    /**
     * Check if unlimited XOR is working.
     */
    @Test
    public void testListAllForMenu_Limited() {
        Pageable pageable = PageRequest.of(0, 50);
        List<Category> categories = categoryRepository.listAllForMenu(1000,
                                                                      "limited",
                                                                       pageable);
        Assert.assertTrue(categories.isEmpty());
    }

    @Test
    public void testListAllForMenu_NotEmpty_Limited() {
        Pageable pageable = PageRequest.of(0, 10);

        List<Category> categories = categoryRepository.listAllForMenu(1, "limited", pageable);
        Assert.assertFalse(categories.isEmpty());
    }

    @Test
    public void testListAllForMenu_NotEmpty_Pageable() {
        int expected = 1;
        Pageable pageable = PageRequest.of(0, expected);

        List<Category> categories = categoryRepository.listAllForMenu(1, "limited", pageable);
        Assert.assertFalse(categories.isEmpty());
        Assert.assertEquals(expected, categories.size());
    }

    @Rollback
    @Test
    public void testSaveCategory() {
        SubCategory sub1 = new SubCategory();
        sub1.setDescription("SubDesc1");
        sub1.setName("SubName1");
        sub1.setGenerous(Generous.NotApply);

        Category category = new Category();
        category.setDescription("Desc");
        category.setName("Name");
        category.setPriority(1);

        category.addSubCategory(sub1);
        sub1.setCategory(category);

        categoryRepository.save(category);

        Assert.assertNotNull(category.getId());
        Assert.assertNotNull(sub1.getId());
    }
}