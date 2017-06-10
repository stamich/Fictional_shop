package pl.multishop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by michal on 30.04.17.
 */

@Controller
public class HomeController // Klasa kontrolera strony głównej - warstwa prezentacji.
{
    @RequestMapping("/")
    public String welcome(Model model)
    {
        GregorianCalendar date = new GregorianCalendar();
        int year = date.get(Calendar.YEAR);
        int m = date.get(Calendar.MONTH);
        int month = m + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);
        int second = date.get(Calendar.SECOND);

        model.addAttribute("greeting", "Multishop Polska.");
        model.addAttribute("tagline", "Witamy w internetowym sklepie internetowym.");
        model.addAttribute("description1", "Strona prezentująca asoryment sklepu. Wszelkie uwagi prosimy kierować do autora.");
        model.addAttribute("description2", "Data ostatniej aktualizacji: " + year + "." + month + "." + day + " o godz. " + hour + ":" + minute + ":" + second);
        return "welcome";
    }
}
