package br.com.gbvbahia.ecommerce.web.restcontrollers;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Project: ecommerce
 *
 * @author Guilherme Bahia
 * @version 1.0
 * @since 02/06/18
 */
@RestController
public class CartRestController extends RestControllerCommon {

    public CartRestController(ParameterService parameterService) {
        super(parameterService);
    }

    @PostMapping(value = "/cart/items")
    public String addToCart(@RequestBody Map args,
                            HttpServletRequest request) {
        logger.info("Object receveid at /cart/items {}", args);
        return ResultResponse.SUCCESS_JSON.result;
    }

}
