package pl.multishop.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.dao.OrderDao;
import pl.multishop.model.Orders;
import pl.multishop.service.OrderServiceImpl;

public class OrderServiceImplTest {

    @Mock
    OrderDao orderDao;

    @InjectMocks
    OrderServiceImpl orderService;

    @Spy
    List<Orders> orderss = new ArrayList<Orders>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        orderss = getOrdersList();
    }

    @Test
    public void findById(){
        Orders orders = orderss.get(0);
        when(orderDao.findById(anyInt())).thenReturn(orders);
        Assert.assertEquals(orderService.findById(orders.getOrderId()), orders);
    }

    @Test
    public void findOrderByClientId(){
        Orders orders = orderss.get(0);
        when(orderDao.findByClientId(anyString())).thenReturn(orders);
        Assert.assertEquals(orderService.findByClientId(anyString()), orders);
    }

    @Test
    public void saveOrder(){
        doNothing().when(orderDao).saveOrder(any(Orders.class));
        orderService.saveOrder(any(Orders.class));
        verify(orderDao, atLeastOnce()).saveOrder(any(Orders.class));
    }

    @Test
    public void updateOrder(){
        Orders orders = orderss.get(0);
        when(orderDao.findById(anyInt())).thenReturn(orders);
        orderService.updateOrder(orders);
        verify(orderDao, atLeastOnce()).findById(anyInt());
    }

    @Test
    public void deleteOrderById(){
        doNothing().when(orderDao).delOrderById(anyInt());
        orderService.delOrderById(anyInt());
        verify(orderDao, atLeastOnce()).delOrderById(anyInt());
    }

    @Test
    public void findAllOrders(){
        when(orderDao.findAllOrders()).thenReturn(orderss);
        Assert.assertEquals(orderService.findAllOrders(), orderss);
    }

    @Test
    public void isOrderNumberUnique(){
        Orders orders = orderss.get(0);
        when(orderDao.findByClientId(anyString())).thenReturn(orders);
        Assert.assertEquals(orderService.isOrderNumberUnique(orders.getOrderId(), orders.getClientId()), true);
    }

    public List<Orders> getOrdersList(){

        Orders o1 = new Orders();
        LocalDate date = LocalDate.now();

        o1.setOrderId(1);
        o1.setClientId("1001");
        o1.setProductId(101);
        o1.setAmount(54.99);
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
