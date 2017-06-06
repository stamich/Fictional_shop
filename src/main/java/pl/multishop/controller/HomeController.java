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
        model.addAttribute("greeting", "MZK Bielsko-Biała.");
        model.addAttribute("tagline", "Witamy w internetowym rozkładzie jazdy.");
        model.addAttribute("description", "Strona prezentująca rozkład komunikacji miejskiej MZK Bielsko-Biała. Ważność rozkładu - 1.01.2017-1.01.2018 roku. Wszelkie uwagi prosimy kierować do autora.");
        return "welcome";
    }
}
