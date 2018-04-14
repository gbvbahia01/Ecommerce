package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.controllers.helpers.ItemScreen;
import br.com.gbvbahia.ecommerce.controllers.helpers.ItemFactory;
import br.com.gbvbahia.ecommerce.model.entity.products.ProductImage;
import br.com.gbvbahia.ecommerce.model.enums.KeyPicture;
import br.com.gbvbahia.ecommerce.services.ProductImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController extends  ControllerCommon {

    private final ProductImageService productImageService;

    public IndexController(ProductImageService productImageService) {

        this.productImageService = productImageService;
    }


    @GetMapping({"", "/"})
    public String getIndexPage(Model model) {
        logger.debug("Getting Index page");

        List<ProductImage> productImageList = productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535);

        List<ItemScreen> itemsScreen = ItemFactory.buildItems(productImageList);

        model.addAttribute("hello","Hello Ecommerce");
        model.addAttribute("items",itemsScreen);

        return "index";
    }
}
