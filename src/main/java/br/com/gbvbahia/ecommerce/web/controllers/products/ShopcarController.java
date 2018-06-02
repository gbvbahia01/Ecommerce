package br.com.gbvbahia.ecommerce.web.controllers.products;

import br.com.gbvbahia.ecommerce.component.CookieHandlerComponent;
import br.com.gbvbahia.ecommerce.web.controllers.ControllerCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.orders.ShopcarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@Controller
public class ShopcarController  extends ControllerCommon {

    private final ShopcarService shopcarService;
    private CookieHandlerComponent cookieHandlerComponent;

    public ShopcarController(ParameterService parameterService,
                             ShopcarService shopcarService,
                             CookieHandlerComponent cookieHandlerComponent) {

        super(parameterService);
        this.shopcarService = shopcarService;
        this.cookieHandlerComponent = cookieHandlerComponent;
    }

    @PostMapping("/shopcar/prodImg")
    public String addProductStockToCart(@RequestParam(name = "prodImgId") Long prodImgId,
                                        @RequestParam(name = "redirect") String redirect,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        logger.debug("Shopcar for prodImg:{} requested.", prodImgId);

        return "redirect:" + redirect;
    }
}
