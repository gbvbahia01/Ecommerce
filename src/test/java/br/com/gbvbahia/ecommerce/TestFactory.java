package br.com.gbvbahia.ecommerce;

import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.entity.products.SubCategory;
import br.com.gbvbahia.ecommerce.model.enums.Generous;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;

import java.util.Collections;

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

    public static Category makeCategory(int var) {
        Category category = new Category();
        category.setId(Long.valueOf(var));
        category.setName("Cat_" + var + "_idx");
        category.setDescription("Cat_" + var + "_Description");
        category.setPriority(var);
        SubCategory sub = makeSubCategory(var);
        sub.setCategory(category);
        category.setSubCategorys(Collections.singleton(sub));
        return category;
    }

    private static SubCategory makeSubCategory(int var) {
        SubCategory sub = new SubCategory();
        sub.setDescription("Sub_" + var + "_Description");
        sub.setGenerous(var % 2 == 0 ? Generous.Female : Generous.Male);
        sub.setName("Sub_" + var + "_idx");
        return sub;
    }
}
