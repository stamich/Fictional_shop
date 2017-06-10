package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.multishop.service.OrderService;

/**
 * Created by michal on 06.05.17.
 */

@Controller
public class OrderController // Klasa kontrolera zamówień - warstwa usług.
{
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String process()
    {
        orderService.processOrder("P1001", 2);
        return "order";
    }
}