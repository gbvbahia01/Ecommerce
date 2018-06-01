package br.com.gbvbahia.ecommerce;

import br.com.gbvbahia.ecommerce.mappers.DozerMapperBuilderImpl;
import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.model.entity.products.Product;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.entity.products.SubCategory;
import br.com.gbvbahia.ecommerce.model.enums.Generous;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFactory {


    private static Mapper dozerMapper;

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
        category.setSubCategories(Collections.singleton(sub));
        return category;
    }

    private static SubCategory makeSubCategory(int var) {
        SubCategory sub = new SubCategory();
        sub.setDescription("Sub_" + var + "_Description");
        sub.setGenerous(var % 2 == 0 ? Generous.Female : Generous.Male);
        sub.setName("Sub_" + var + "_idx");
        return sub;
    }

    public static final String EMAIL_TEST_VALUE = "est@test.com";
    public static final String WHATS_TEST_VALUE = "+1 55555 5555";
    public static final String FACEB_TEST_VALUE = "facebook.com/user/123456";

    public static List<ParameterDTO> makeContactParameters() {
        List<ParameterDTO> contacts = new ArrayList<>();
        contacts.add(new ParameterDTO("CONTACT_EMAIL", EMAIL_TEST_VALUE, true));
        contacts.add(new ParameterDTO("CONTACT_WHATS", WHATS_TEST_VALUE, true));
        contacts.add(new ParameterDTO("CONTACT_FACEBOOK", FACEB_TEST_VALUE, true));
        return contacts;
    }

    public static Mapper getDozerForUnitTest() {
        return DozerMapperBuilderImpl.getInstance().getMapper();
    }
}
