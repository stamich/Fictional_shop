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
import pl.multishop.model.Client;
import pl.multishop.model.Order;
import pl.multishop.model.Product;
import pl.multishop.service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    List<Order> ordersses = new ArrayList<Order>();

    @Spy
    ModelMap modelMap;

    @Mock
    BindingResult bindingResult;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ordersses = getOrdersList();
    }

    @Test
    public void listOrders(){
        when(orderService.findAllOrders()).thenReturn(ordersses);
        Assert.assertEquals(orderController.listOrders(modelMap), "orders");
        Assert.assertEquals(modelMap.get("orders"), ordersses);
        verify(orderService, atLeastOnce()).findAllOrders();
    }

    @Test
    public void newOrder(){
        Assert.assertEquals(orderController.newOrder(modelMap), "creatingOrder");
        Assert.assertNotNull(modelMap.get("orders"));
        Assert.assertFalse((Boolean)modelMap.get("edit"));
        //Assert.assertEquals(((Order)modelMap.get("orders")).getOrderId(), 0);
    }

    @Test
    public void saveOrderWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(orderService).saveOrder(any(Order.class));
        Assert.assertEquals(orderController.saveOrder(ordersses.get(0), bindingResult, modelMap), "addOrder");
    }

    @Test
    public void saveOrderWithValidationErrorNonUniqueSSN(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyLong(), anyLong())).thenReturn(false);
        Assert.assertEquals(orderController.saveOrder(ordersses.get(0), bindingResult, modelMap), "addOrder");
    }

    @Test
    public void saveOrderWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyLong(), anyLong())).thenReturn(true);
        doNothing().when(orderService).saveOrder(any(Order.class));
        Assert.assertEquals(orderController.saveOrder(ordersses.get(0), bindingResult, modelMap), "orderSuccess");
        Assert.assertEquals(modelMap.get("success"), "Zamowienie numer 2 zapisane pomyslnie!");
    }

    @Test
    public void editOrder(){
        Order order = ordersses.get(0);
        when(orderService.findById(anyLong())).thenReturn(order);
        Assert.assertEquals(orderController.editOrder(anyLong(), modelMap), "addOrder");
        Assert.assertNotNull(modelMap.get("order"));
        Assert.assertTrue((Boolean)modelMap.get("edit"));
        //Assert.assertEquals(((Order)modelMap.get("order")).getOrderId(), 2);
    }

    @Test
    public void updateOrderWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(orderService).updateOrder(any(Order.class));
        Assert.assertEquals(orderController.updateOrder(ordersses.get(0), bindingResult, modelMap,1), "addOrder");
    }

    @Test
    public void updateOrderWithValidationErrorNonUniqueNumber(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyLong(), anyLong())).thenReturn(false);
        Assert.assertEquals(orderController.updateOrder(ordersses.get(0), bindingResult, modelMap,1), "addOrder");
    }

    @Test
    public void updateOrderWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(orderService.isOrderNumberUnique(anyLong(), anyLong())).thenReturn(true);
        doNothing().when(orderService).updateOrder(any(Order.class));
        Assert.assertEquals(orderController.updateOrder(ordersses.get(0), bindingResult, modelMap, 1), "orderSuccess");
        Assert.assertEquals(modelMap.get("success"), "Zamowienie numer 2 zmienione pomyslnie!");
    }

    @Test
    public void deleteOrder(){
        doNothing().when(orderService).delOrderById(anyInt());
        Assert.assertEquals(orderController.deleteOrder(1), "redirect:/orderList");
    }

    public List<Order> getOrdersList(){

        Order o1 = new Order();
        Client client = new Client();
        Set<Product> products = new HashSet<>();
        LocalDateTime date = LocalDateTime.now();

        o1.setId(1L);
        o1.setClient(client);
        o1.setProducts(products);
        o1.setAmount(54.99);
        o1.setCreatedAt(date);
        o1.setStatus("Send");

        Order o2 = new Order();

        o1.setId(2L);
        o1.setClient(client);
        o1.setProducts(products);
        o1.setAmount(77.99);
        o1.setCreatedAt(date);
        o1.setStatus("Prepared");

        ordersses.add(o1);
        ordersses.add(o2);
        return ordersses;
    }

}
