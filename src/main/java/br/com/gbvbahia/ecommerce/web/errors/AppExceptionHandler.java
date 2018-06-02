package br.com.gbvbahia.ecommerce.web.errors;

import br.com.gbvbahia.ecommerce.web.controllers.ControllerCommon;
import br.com.gbvbahia.ecommerce.exceptions.NotFoundException;
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
public class AppExceptionHandler extends ControllerCommon {

    public AppExceptionHandler(ParameterService parameterService) {
        super(parameterService);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleInternalServerError(Exception exception) {

        logger.error(exception.getMessage());
        logger.error("Exception:", exception);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(Pages.INTERNAL_ERROR_500.pageName);
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}