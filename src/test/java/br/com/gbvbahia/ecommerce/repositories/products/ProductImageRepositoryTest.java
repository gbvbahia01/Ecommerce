package br.com.gbvbahia.ecommerce.repositories.products;

import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductImageRepositoryTest {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listByPromotionTagCampaingProductTrue() {
        int amountTrueExpected = 12;

        List<ProductImage> result = productImageRepository.listByPromotionTag(true,
                                                                              true,
                                                                               KeyPicture.SIZE_420_535);
        Assert.assertTrue("ProductImage actives expected problem",
                          result.size() == amountTrueExpected);
    }

    @Test
    public void listByPromotionTagCampaingProductFalse() {
        int amountTrueExpected = 3;

        List<ProductImage> result = productImageRepository.listByPromotionTag(true,
                false,
                KeyPicture.SIZE_420_535);
        Assert.assertTrue("ProductImage actives expected problem",
                result.size() == amountTrueExpected);
    }

    @Test
    public void listByPromotionTagCampaingFalse() {
        int amountTrueExpected = 0;

        List<ProductImage> result = productImageRepository.listByPromotionTag(false,
                true,
                KeyPicture.SIZE_420_535);
        Assert.assertTrue("ProductImage actives expected problem",
                result.size() == amountTrueExpected);
    }
}