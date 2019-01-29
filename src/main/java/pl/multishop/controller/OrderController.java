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
import pl.multishop.model.Order;
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
        List<Order> orders = orderService.findAllOrders();
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
        Order order = new Order();
        modelMap.addAttribute( "orders", order);
        modelMap.addAttribute("edit", false);
        return "creatingOrder";
    }

    /**
     * This method saves created order (REST - POST).
     * @param order
     * @param bindingResult
     * @param modelMap
     * @return orderSuccess
     */
    @RequestMapping(value = { "/newOrder" }, method = RequestMethod.POST)
    public String saveOrder(@Valid Order order, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            return "addOrder";
        }

//        if(!orderService.isOrderNumberUnique(order.getId(), order.getClient()))){
//            FieldError clientIdError = new FieldError("order", "clientId",
//                    messageSource.getMessage("non.unique.clientId", new String[]{order.getClientId()}, Locale.getDefault()));
//            bindingResult.addError(clientIdError);
//            return "addOrder";
//        }

        orderService.saveOrder(order);
        modelMap.addAttribute("success", "Zamowienie numer " + order.getId() + " zapisane pomyslnie!");
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
        Order order = orderService.findById(orderId);
        modelMap.addAttribute("orders", order);
        modelMap.addAttribute("edit", true);
        return "addOrder";
    }

    /**
     * This method allows to update order properties (REST - POST).
     * @param order
     * @param bindingResult
     * @param modelMap
     * @param orderId
     * @return orderSuccess
     */
    @RequestMapping(value = { "/edit-{orderId}-order" }, method = RequestMethod.POST)
    public String updateOrder(@Valid Order order, BindingResult bindingResult, ModelMap modelMap, @PathVariable int orderId){

        if(bindingResult.hasErrors()){
            return "addOrder";
        }

//        if(!orderService.isOrderNumberUnique(order.getOrderId(), order.getClientId())){
//            FieldError clientIdError = new FieldError("order", "clientId",
//                    messageSource.getMessage("non.unique.clientId", new String[]{order.getClientId()}, Locale.getDefault()));
//            bindingResult.addError(clientIdError);
//            return "addOrder";
//        }

        orderService.saveOrder(order);
        modelMap.addAttribute("success", "Zamowienie numer " + order.getId() + " zmienione pomyslnie!");
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