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

        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        ModelAndView modelAndView = new ModelAndView();


        switch (status) {
            case 400: { //"Http Error Code: 400. Bad Request"
                break;
            }
            case 401: { //"Http Error Code: 401. Unauthorized"
                break;
            }
            case 404: { //"Http Error Code: 404. Resource not found"
                logger.warn("Handling: HttpStatus.NOT_FOUND");
                modelAndView.setViewName("404_NOT_FOUND".toLowerCase());
                modelAndView.addObject("exception", exception);
                return modelAndView;
            }

            case 500:
            default: { // Http Error Code: 500. Internal Server Error
                logger.warn("Handling: {}", status);
                logger.error("Handling: HttpStatus.INTERNAL_SERVER_ERROR", exception);
                modelAndView.setViewName("INTERNAL_SERVER_ERROR".toLowerCase());
                modelAndView.addObject("exception", exception);
                return modelAndView;
            }
        }


        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
