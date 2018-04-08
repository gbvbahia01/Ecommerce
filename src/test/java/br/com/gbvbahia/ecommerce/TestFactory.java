package br.com.gbvbahia.ecommerce;

import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;

public class TestFactory {


    public static ProductImage makeProductImage(int var) {
        ProductImage productImage = new ProductImage();
        productImage.setId(Long.valueOf(var));
        productImage.setKeyPicture(KeyPicture.SIZE_420_535);
        productImage.setNamePicture("img" + var + ".png");

        Product product = new Product();
        product.setPrice(1.0F + var);
        product.setDescription("Description " + var);
        product.setName("Name " + var);

        productImage.setProduct(product);

        return productImage;
    }
}
