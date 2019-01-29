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
import pl.multishop.model.Order;

public class OrderServiceImplTest {

    @Mock
    OrderDao orderDao;

    @InjectMocks
    OrderServiceImpl orderService;

    @Spy
    List<Order> ordersses = new ArrayList<Order>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ordersses = getOrdersList();
    }

    @Test
    public void findById(){
        Order order = ordersses.get(0);
        when(orderDao.findById(anyInt())).thenReturn(order);
        Assert.assertEquals(orderService.findById(order.getOrderId()), order);
    }

    @Test
    public void findOrderByClientId(){
        Order order = ordersses.get(0);
        when(orderDao.findByClientId(anyString())).thenReturn(order);
        Assert.assertEquals(orderService.findByClientId(anyString()), order);
    }

    @Test
    public void saveOrder(){
        doNothing().when(orderDao).saveOrder(any(Order.class));
        orderService.saveOrder(any(Order.class));
        verify(orderDao, atLeastOnce()).saveOrder(any(Order.class));
    }

    @Test
    public void updateOrder(){
        Order order = ordersses.get(0);
        when(orderDao.findById(anyInt())).thenReturn(order);
        orderService.updateOrder(order);
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
        when(orderDao.findAllOrders()).thenReturn(ordersses);
        Assert.assertEquals(orderService.findAllOrders(), ordersses);
    }

    @Test
    public void isOrderNumberUnique(){
        Order order = ordersses.get(0);
        when(orderDao.findByClientId(anyString())).thenReturn(order);
        Assert.assertEquals(orderService.isOrderNumberUnique(order.getOrderId(), order.getClientId()), true);
    }

    public List<Order> getOrdersList(){

        Order o1 = new Order();
        LocalDate date = LocalDate.now();

        o1.setOrderId(1);
        o1.setClientId("1001");
        o1.setProductId(101);
        o1.setAmount(54.99);
        o1.setOrderDate(date);
        o1.setOrderStatus("Send");

        Order o2 = new Order();

        o1.setOrderId(2);
        o1.setClientId("1002");
        o1.setProductId(102);
        o1.setAmount(77.99);
        o1.setOrderDate(date);
        o1.setOrderStatus("Prepared");

        ordersses.add(o1);
        ordersses.add(o2);
        return ordersses;
    }

}
