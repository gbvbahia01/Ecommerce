package br.com.gbvbahia.ecommerce.services.dto;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemScreenFactoryTest {

    private Mapper mapper;


    @Before
    public void setUp() {
        mapper = TestFactory.getDozerForUnitTest();
    }

    @Test
    public void testBuildItemFull() {
        ProductImage productImage = TestFactory.makeProductImage(1);

        ProductImageDTO dto = mapper.map(productImage, ProductImageDTO.class);

        Assert.assertEquals(productImage.getId(), dto.getId());
        Assert.assertEquals(productImage.getProduct().getDescription(), dto.getProduct().getDescription());
        Assert.assertEquals(productImage.getKeyPicture().getHeight(), dto.getKeyPicture().getHeight());
        Assert.assertEquals(productImage.getKeyPicture().getWidth(), dto.getKeyPicture().getWidth());
        Assert.assertEquals(productImage.getNamePicture(), dto.getNamePicture());
        Assert.assertEquals(productImage.getProduct().getPrice(), dto.getProduct().getPrice());
    }

    @Test
    public void testBuildItemEmptyName() {
        ProductImage productImage = TestFactory.makeProductImage(1);

        productImage.setNamePicture(null);

        ProductImageDTO dto = mapper.map(productImage, ProductImageDTO.class);

        Assert.assertEquals(productImage.getId(), dto.getId());
        Assert.assertEquals(productImage.getProduct().getDescription(), dto.getProduct().getDescription());
        Assert.assertEquals(productImage.getKeyPicture().getHeight(), dto.getKeyPicture().getHeight());
        Assert.assertEquals(productImage.getKeyPicture().getWidth(), dto.getKeyPicture().getWidth());
        Assert.assertEquals(productImage.getKeyPicture().getDefaultImg(), dto.getNamePicture());
        Assert.assertEquals(productImage.getProduct().getPrice(), dto.getProduct().getPrice());
    }
}