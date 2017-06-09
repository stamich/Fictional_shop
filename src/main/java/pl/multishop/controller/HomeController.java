package pl.multishop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by michal on 31.05.17.
 */

@Controller
public class HomeController
{

    @RequestMapping("/")
    public String welcome(Model model)
    {
        model.addAttribute("greeting", "Multishop Polska.");
        model.addAttribute("tagline", "Witamy w internetowym sklepie internetowym.");
        model.addAttribute("description", "Strona prezentująca asoryment sklepu. Wszelkie uwagi prosimy kierować do autora.");
        return "welcome";
    }
}
