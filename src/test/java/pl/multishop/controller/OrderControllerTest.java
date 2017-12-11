package pl.multishop.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.controller.OrderController;
import pl.multishop.model.Orders;
import pl.multishop.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    OrderService orderService;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    OrderController orderController ;

    @Spy
    List<Orders> orderss = new ArrayList<Orders>();

    @Spy
    ModelMap modelMap;

    @Mock
    BindingResult bindingResult;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        orderss = getOrdersList();
    }

    @Test
    public void listOrders(){
        when(orderService.findAllOrders()).thenReturn(orderss);
        Assert.assertEquals(orderController.listOrders(modelMap), "orders");
        Assert.assertEquals(modelMap.get("orders"), orderss);
        verify(orderService, atLeastOnce()).findAllOrders();
    }

    @Test
    public void newOrder(){
        Assert.assertEquals(orderController.newOrder(modelMap), "creatingOrder");
        Assert.assertNotNull(modelMap.get("orders"));
        Assert.assertFalse((Boolean)modelMap.get("edit"));
        Assert.assertEquals(((Orders)modelMap.get("orders")).getOrderId(), 0);
    }

    @Test
    public void saveOrderWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(orderService).saveOrder(any(Orders.class));
        Assert.assertEquals(orderController.saveOrder(orderss.get(0), bindingResult, modelMap), "addOrder");
    }

    @Test
    public void saveOrderWithValidationErrorNonUniqueSSN(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyInt(), anyString())).thenReturn(false);
        Assert.assertEquals(orderController.saveOrder(orderss.get(0), bindingResult, modelMap), "addOrder");
    }

    @Test
    public void saveOrderWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyInt(), anyString())).thenReturn(true);
        doNothing().when(orderService).saveOrder(any(Orders.class));
        Assert.assertEquals(orderController.saveOrder(orderss.get(0), bindingResult, modelMap), "orderSuccess");
        Assert.assertEquals(modelMap.get("success"), "Zamowienie numer 2 zapisane pomyslnie!");
    }

    @Test
    public void editOrder(){
        Orders orders = orderss.get(0);
        when(orderService.findById(anyInt())).thenReturn(orders);
        Assert.assertEquals(orderController.editOrder(anyInt(), modelMap), "addOrder");
        Assert.assertNotNull(modelMap.get("orders"));
        Assert.assertTrue((Boolean)modelMap.get("edit"));
        Assert.assertEquals(((Orders)modelMap.get("orders")).getOrderId(), 2);
    }

    @Test
    public void updateOrderWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(orderService).updateOrder(any(Orders.class));
        Assert.assertEquals(orderController.updateOrder(orderss.get(0), bindingResult, modelMap,1), "addOrder");
    }

    @Test
    public void updateOrderWithValidationErrorNonUniqueNumber(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyInt(), anyString())).thenReturn(false);
        Assert.assertEquals(orderController.updateOrder(orderss.get(0), bindingResult, modelMap,1), "addOrder");
    }

    @Test
    public void updateOrderWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyInt(), anyString())).thenReturn(true);
        doNothing().when(orderService).updateOrder(any(Orders.class));
        Assert.assertEquals(orderController.updateOrder(orderss.get(0), bindingResult, modelMap, 1), "orderSuccess");
        Assert.assertEquals(modelMap.get("success"), "Zamowienie numer 2 zmienione pomyslnie!");
    }

    @Test
    public void deleteOrder(){
        doNothing().when(orderService).delOrderById(anyInt());
        Assert.assertEquals(orderController.deleteOrder(1), "redirect:/orderList");
    }

    public List<Orders> getOrdersList(){

        Orders o1 = new Orders();
        LocalDate date = LocalDate.now();

        o1.setOrderId(1);
        o1.setClientId("1001");
        o1.setProductId(101);
        o1.setAmount(55.99);
        o1.setOrderDate(date);
        o1.setOrderStatus("Send");

        Orders o2 = new Orders();

        o1.setOrderId(2);
        o1.setClientId("1002");
        o1.setProductId(102);
        o1.setAmount(77.99);
        o1.setOrderDate(date);
        o1.setOrderStatus("Prepared");

        orderss.add(o1);
        orderss.add(o2);
        return orderss;
    }

}
