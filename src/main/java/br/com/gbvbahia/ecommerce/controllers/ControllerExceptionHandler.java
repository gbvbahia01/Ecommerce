package br.com.gbvbahia.ecommerce.controllers;

import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 15/04/18
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ControllerCommon {

    public ControllerExceptionHandler(ParameterService parameterService) {
        super(parameterService);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleInternalServerError(Exception exception){

        logger.error("Exception");
        logger.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("INTERNAL_SERVER_ERROR".toLowerCase());
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}