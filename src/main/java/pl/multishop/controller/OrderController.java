package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.multishop.model.Orders;
import pl.multishop.service.OrderService;

import java.util.List;

/**
 * Created by michal on 06.05.17.
 */

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = { "/ordersList" }, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap) {
        List<Orders> orders = orderService.findAllOrders();
        modelMap.addAttribute("orders", orders);
        return "orders";
    }

    @RequestMapping(value = { "/newOrder" }, method = RequestMethod.GET)
    public String newOrder(ModelMap modelMap){
        Orders orders = new Orders();
        modelMap.addAttribute( "orders", orders);
        modelMap.addAttribute("edit", false);
        return "creatingOrder";
    }

}