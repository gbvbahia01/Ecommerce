package br.com.gbvbahia.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends  ControllerCommon {


    public IndexController() {
    }


    @RequestMapping({"", "/", "/ecommerce"})
    public String getIndexPage(Model model) {
        logger.debug("Getting Index page");

        model.addAttribute("hello","Hello Ecommerce");

        return "index";
    }

}
