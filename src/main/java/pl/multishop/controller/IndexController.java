package pl.multishop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This simple class contains the main application's controller.
 * @author Michal Stawarski
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(){
        return "welcome";
    }

    @RequestMapping(value = {"/productView"}, method = RequestMethod.GET)
    public String productPage(){
        return "product";
    }
}
