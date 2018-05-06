package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.controllers.helpers.ItemScreen;
import br.com.gbvbahia.ecommerce.controllers.helpers.ItemFactory;
import br.com.gbvbahia.ecommerce.model.entity.commons.Parameter;
import br.com.gbvbahia.ecommerce.model.entity.products.Category;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.products.CategoryService;
import br.com.gbvbahia.ecommerce.services.products.ProductImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Controller
public class IndexController extends  ControllerCommon {

    private final ProductImageService productImageService;
    private final CategoryService categoryService;

    public IndexController(ParameterService parameterService,
                           ProductImageService productImageService,
                           CategoryService categoryService) {
        super(parameterService);
        this.productImageService = productImageService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public String getIndexPage(final Model model) {
        logger.debug("Getting Index page");

        List<ProductImage> productImageList = productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535);
        List<Category> categoryList = categoryService.listCategoriesForMenu();
        List<Parameter> contactParameters = parameterService.listByRange(ParameterService.CONTACT_PARAMETERS);

        List<ItemScreen> itemsProductImage = ItemFactory.buildItemsFromProductImage(productImageList);
        List<ItemScreen> itemsCategory = ItemFactory.buildItemsFromCategory(categoryList);
        List<ItemScreen> itemsContact = ItemFactory.buildItemsFromParameters(contactParameters);

        Integer contacts = 0;
        for (ItemScreen itemParameter : itemsContact) {
            model.addAttribute(itemParameter.getName(), itemParameter);
            if (itemParameter.isRendered()) {
                contacts++;
            }
        };

        model.addAttribute("hello","Hello Ecommerce");
        model.addAttribute("promotionItems",itemsProductImage);
        model.addAttribute("categories", itemsCategory);
        model.addAttribute("contacts", contacts);

        return "index";
    }
}
