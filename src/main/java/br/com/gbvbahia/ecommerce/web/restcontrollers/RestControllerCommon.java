package br.com.gbvbahia.ecommerce.web.restcontrollers;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import br.com.gbvbahia.ecommerce.web.component.CookieHandlerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
public abstract class RestControllerCommon {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ParameterService parameterService;

    protected final CookieHandlerComponent cookieHandler = new CookieHandlerComponent();

    protected enum ResultResponse {
        SUCCESS_JSON("{\"msg\":\"success\"}"),
        ERROR_JSON("error");

        public final String result;

        ResultResponse(String result) {
            this.result = result;
        }
    }

    public RestControllerCommon(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
