package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.multishop.model.Orders;
import pl.multishop.service.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * This class of presentation layer provides order controller.
 * @author Michal Stawarski
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSource messageSource;

    /**
     * This method lists all exisiting orders (REST - GET).
     * @param modelMap
     * @return orders
     */
    @RequestMapping(value = { "/ordersList" }, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap) {
        List<Orders> orders = orderService.findAllOrders();
        modelMap.addAttribute("orders", orders);
        return "orders";
    }

    /**
     * This method creates the new order (REST - GET).
     * @param modelMap
     * @return creatingOrder
     */
    @RequestMapping(value = { "/newOrder" }, method = RequestMethod.GET)
    public String newOrder(ModelMap modelMap){
        Orders orders = new Orders();
        modelMap.addAttribute( "orders", orders);
        modelMap.addAttribute("edit", false);
        return "creatingOrder";
    }

    /**
     * This method saves created order (REST - POST).
     * @param orders
     * @param bindingResult
     * @param modelMap
     * @return orderSuccess
     */
    @RequestMapping(value = { "/newOrder" }, method = RequestMethod.POST)
    public String saveOrder(@Valid Orders orders, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            return "addOrder";
        }

        if(!orderService.isOrderNumberUnique(orders.getOrderId(), orders.getClientId())){
            FieldError clientIdError = new FieldError("orders", "clientId",
                    messageSource.getMessage("non.unique.clientId", new String[]{orders.getClientId()}, Locale.getDefault()));
            bindingResult.addError(clientIdError);
            return "addOrder";
        }

        orderService.saveOrder(orders);
        modelMap.addAttribute("success", "Zamowienie numer " + orders.getOrderId() + " zapisane pomyslnie!");
        return "orderSuccess";
    }

    /**
     * This method allows to edit order properties (REST - GET).
     * @param orderId
     * @param modelMap
     * @return addOrder
     */
    @RequestMapping(value = { "/edit-{orderId}-order" }, method = RequestMethod.GET)
    public String editOrder(@PathVariable int orderId, ModelMap modelMap){
        Orders orders = orderService.findById(orderId);
        modelMap.addAttribute("orders", orders);
        modelMap.addAttribute("edit", true);
        return "addOrder";
    }

    /**
     * This method allows to update order properties (REST - POST).
     * @param orders
     * @param bindingResult
     * @param modelMap
     * @param orderId
     * @return orderSuccess
     */
    @RequestMapping(value = { "/edit-{orderId}-order" }, method = RequestMethod.POST)
    public String updateOrder(@Valid Orders orders, BindingResult bindingResult, ModelMap modelMap, @PathVariable int orderId){

        if(bindingResult.hasErrors()){
            return "addOrder";
        }

        if(!orderService.isOrderNumberUnique(orders.getOrderId(), orders.getClientId())){
            FieldError clientIdError = new FieldError("orders", "clientId",
                    messageSource.getMessage("non.unique.clientId", new String[]{orders.getClientId()}, Locale.getDefault()));
            bindingResult.addError(clientIdError);
            return "addOrder";
        }

        orderService.saveOrder(orders);
        modelMap.addAttribute("success", "Zamowienie numer " + orders.getOrderId() + " zmienione pomyslnie!");
        return "orderSuccess";
    }

    /**
     * This method deletes existing order by it id number (REST - GET).
     * @param orderId
     * @return redirect:/orderList
     */
    @RequestMapping(value = { "/delete-{orderId}-order" }, method = RequestMethod.GET)
    public String deleteOrder(@PathVariable int orderId){
        orderService.delOrderById(orderId);
        return "redirect:/orderList";
    }

}