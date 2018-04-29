package br.com.gbvbahia.ecommerce.controllers.helpers;

import br.com.gbvbahia.ecommerce.TestFactory;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import org.junit.Assert;
import org.junit.Test;

public class ItemScreenFactoryTest {


    @Test
    public void testBuildItemFull() {
        ProductImage productImage = TestFactory.makeProductImage(1);

        ItemScreen itemScreen = ItemFactory.buildItem(productImage);

        Assert.assertEquals(productImage.getId(), itemScreen.getId());
        Assert.assertEquals(productImage.getClass(), itemScreen.getClazz());
        Assert.assertEquals(productImage.getProduct().getDescription(), itemScreen.getDescription());
        Assert.assertEquals(productImage.getKeyPicture().getHeight(), itemScreen.getImgHeight());
        Assert.assertEquals(productImage.getKeyPicture().getWidth(), itemScreen.getImgWidth());
        Assert.assertEquals(productImage.getNamePicture(), itemScreen.getImgName());
        Assert.assertEquals(productImage.getProduct().getPrice(), Float.valueOf(itemScreen.getPrice()));
    }

    @Test
    public void testBuildItemEmptyName() {
        ProductImage productImage = TestFactory.makeProductImage(1);

        productImage.setNamePicture(null);

        ItemScreen itemScreen = ItemFactory.buildItem(productImage);

        Assert.assertEquals(productImage.getId(), itemScreen.getId());
        Assert.assertEquals(productImage.getClass(), itemScreen.getClazz());
        Assert.assertEquals(productImage.getProduct().getDescription(), itemScreen.getDescription());
        Assert.assertEquals(productImage.getKeyPicture().getHeight(), itemScreen.getImgHeight());
        Assert.assertEquals(productImage.getKeyPicture().getWidth(), itemScreen.getImgWidth());
        Assert.assertEquals(productImage.getKeyPicture().getDefaultImg(), itemScreen.getImgName());
        Assert.assertEquals(productImage.getProduct().getPrice(), Float.valueOf(itemScreen.getPrice()));
    }
}