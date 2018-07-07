package br.com.gbvbahia.ecommerce.web.restcontrollers;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.services.dto.orders.ShopCartDTO;
import br.com.gbvbahia.ecommerce.services.dto.products.ProductStockDTO;
import br.com.gbvbahia.ecommerce.services.orders.ShopCartService;
import br.com.gbvbahia.ecommerce.services.products.ProductService;
import br.com.gbvbahia.ecommerce.util.UniqueId;
import br.com.gbvbahia.ecommerce.web.component.CookieHandlerComponent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 02/06/18
 */
@RestController
public class ShopCartRestController extends RestControllerCommon {


    private final ProductService productService;
    private final ShopCartService shopCartService;

    public ShopCartRestController(ParameterService parameterService,
                                  ProductService productService,
                                  ShopCartService shopCartService) {
        super(parameterService);
        this.productService = productService;
        this.shopCartService = shopCartService;
    }

    @GetMapping(value = "/cart/items/count")
    @ResponseBody
    public Map<String, Object> getCartItemCount(HttpServletRequest request, HttpServletResponse response)  {
        String shopcarCookieKey = parameterService.getValueByKey(ParameterService.APP_COOKIE_SHOPCAR_KEY);
        Map<String, Object> map = new HashMap<>();
        map.put("count", 0);

        if (StringUtils.isBlank(shopcarCookieKey)){
            logger.error("Paremeter {} not found. Coockies cannot be handled.", ParameterService.APP_COOKIE_SHOPCAR_KEY);
            return map;
        }

        Cookie shopCookie = cookieHandler.getCookie(request, shopcarCookieKey);

        if (shopCookie == null || StringUtils.isBlank(shopCookie.getValue())) {
            logger.debug("No items found in shopcart");
            return map;
        }

        ShopCartDTO shopCart = shopCartService.findBySerial(shopCookie.getValue());

        if (shopCart == null) {
            return map;
        } else {
            map.put("count", shopCart.getShopCartProducts().size());
        }

        return map;
    }

    @PostMapping(value = "/cart/items")
    public String addToCart(@RequestBody final Map<String, String> args,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        logger.debug("Object receveid at /cart/items {}", args);

        List<Long> productStockIds = new ArrayList<>();

        args.keySet().forEach(key -> {

            if (StringUtils.startsWith(key, "id_")) {
                final String keyLong = args.get(key);
                try {
                    productStockIds.add(Long.valueOf(keyLong));
                } catch (NumberFormatException nf) {
                    logger.error("Cannot conver {} to number", keyLong);
                }
            }
        });

        List<ProductStockDTO> productStockDTOList = productService.listProductStockId(productStockIds);

        if (!productStockDTOList.isEmpty()) {
            ShopCartDTO shopCart = managerShopcar(request, response);

            if (shopCart != null) {

                shopCart.addProducts(productStockDTOList.toArray(new ProductStockDTO[productStockDTOList.size()]));
                shopCartService.saveShopcar(shopCart);
            }
        }

        return ResultResponse.SUCCESS_JSON.result;
    }

    protected ShopCartDTO managerShopcar(HttpServletRequest request, HttpServletResponse response) {
        String shopcarCookieKey = parameterService.getValueByKey(ParameterService.APP_COOKIE_SHOPCAR_KEY);

        if (StringUtils.isBlank(shopcarCookieKey)){
            logger.error("Paremeter {} not found. Coockies cannot be handled.", ParameterService.APP_COOKIE_SHOPCAR_KEY);
            return null;
        }

        Cookie shopCookie = cookieHandler.getCookie(request, shopcarCookieKey);

        ShopCartDTO shopcartDto;
        String shopcarCookieValue;
        if (shopCookie == null || StringUtils.isBlank(shopCookie.getValue())) {
            shopcarCookieValue = UniqueId.createUniqueId();
            shopcartDto = shopCartService.saveShopcar(new ShopCartDTO(shopcarCookieValue));
        } else {
            shopcartDto = shopCartService.findBySerial(shopCookie.getValue());
            shopcarCookieValue = shopCookie.getValue();
            if (shopcartDto == null) {
                shopcartDto = shopCartService.saveShopcar(new ShopCartDTO(shopcarCookieValue));
            }
        }
            cookieHandler.setCookie(request, response,
                                    shopcarCookieKey, shopcarCookieValue,
                                    cookieHandler.getCookieSeconds(CookieHandlerComponent.MAX_COOKIES_DAYS));
        return shopcartDto;
    }
}
