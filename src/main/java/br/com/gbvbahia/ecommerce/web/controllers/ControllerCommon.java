package br.com.gbvbahia.ecommerce.web.controllers;

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
public abstract class ControllerCommon {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final ParameterService parameterService;
    protected final CookieHandlerComponent cookieHandler;

    protected enum Pages {
        NOT_FOUND_404("404_not_found"),
        INTERNAL_ERROR_500("internal_server_error"),
        INDEX("index");

        public final String pageName;

        Pages(String pageName) {
            this.pageName = pageName;
        }
    }

    public ControllerCommon(ParameterService parameterService) {

        this.parameterService = parameterService;
        cookieHandler = new CookieHandlerComponent(parameterService);
    }
}
