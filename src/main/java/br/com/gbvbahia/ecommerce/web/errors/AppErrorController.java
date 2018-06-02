package br.com.gbvbahia.ecommerce.web.errors;

import br.com.gbvbahia.ecommerce.web.controllers.ControllerCommon;
import br.com.gbvbahia.ecommerce.services.commons.ParameterService;
import org.springframework.boot.web.servlet.error.ErrorController;
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
public class AppErrorController extends ControllerCommon implements ErrorController {

    public AppErrorController(ParameterService parameterService) {
        super(parameterService);
    }

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
                modelAndView.setViewName(Pages.NOT_FOUND_404.pageName);
                modelAndView.addObject("exception", exception);
                return modelAndView;
            }

            case 500:
            default: { // Http Error Code: 500. Internal Server Error
                logger.warn("Handling: {}", status);
                logger.error("Handling: HttpStatus.INTERNAL_SERVER_ERROR", exception);
                modelAndView.setViewName(Pages.INTERNAL_ERROR_500.pageName);
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
