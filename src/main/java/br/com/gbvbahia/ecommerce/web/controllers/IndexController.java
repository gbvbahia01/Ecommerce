package br.com.gbvbahia.ecommerce.web.controllers;

import br.com.gbvbahia.ecommerce.services.dto.commons.ParameterDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.CategoryDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductImageDTO;
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

    @GetMapping({"", "/", "/ecommerce"})
    public String getIndexPage(final Model model) {
        logger.debug("Getting Index page");

        List<ProductImageDTO> productImageList = productImageService.listActivesByKeyPicture(KeyPicture.SIZE_420_535);
        List<CategoryDTO> categoryList = categoryService.listCategoriesForMenu();
        List<ParameterDTO> contactParameters = parameterService.listByRange(ParameterService.CONTACT_PARAMETERS);


        Integer contacts = 0;
        for (ParameterDTO parameterDTO : contactParameters) {
            if (parameterDTO.isActivated()) {
                model.addAttribute(parameterDTO.getKey(), parameterDTO);
                contacts++;
            }
        }

        model.addAttribute("promotionItems", productImageList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("contacts", contacts);

        return Pages.INDEX.pageName;
    }
}
