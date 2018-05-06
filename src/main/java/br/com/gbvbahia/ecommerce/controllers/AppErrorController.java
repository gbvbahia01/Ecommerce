package br.com.gbvbahia.ecommerce.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Project: Ecommerce
 *
 * @author Guilherme
 * @version 1.0
 * @since 06/05/18
 */
@Controller
public class AppErrorController implements ErrorController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, Exception exception) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView modelAndView = new ModelAndView();

        if (exception != null) {
            logger.error("Handling:", exception);
            modelAndView.setViewName("INTERNAL_SERVER_ERROR".toLowerCase());
            modelAndView.addObject("exception", exception);
            return modelAndView;
        }

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                //modelAndView.setViewName("error-404");
                logger.warn("Handling: HttpStatus.NOT_FOUND");
                modelAndView.setViewName("error");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                //modelAndView.setViewName("error-500");
                logger.warn("Handling: HttpStatus.INTERNAL_SERVER_ERROR");
                modelAndView.setViewName("error");
            }
        }

        logger.warn("Handling: {}", status);
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
