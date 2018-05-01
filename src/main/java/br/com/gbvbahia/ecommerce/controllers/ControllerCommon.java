package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
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

    public ControllerCommon(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
