package pl.multishop.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by michal on 31.05.17.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String welcome(Model model)
    {
        model.addAttribute("greeting", "Welcome in our internet shop.");
        model.addAttribute("tagline", "It's very good shop.");
        return "welcome";
    }
}
