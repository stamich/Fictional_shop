package pl.multishop.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.dao.OrderDao;
import pl.multishop.model.Client;
import pl.multishop.model.Order;
import pl.multishop.model.Product;

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
        when(orderDao.findById(anyLong())).thenReturn(order);
        Assert.assertEquals(orderService.findById(order.getId()), order);
    }

    @Test
    public void findOrderByClientId(){
        Order order = ordersses.get(0);
        when(orderDao.findByClientId(anyLong())).thenReturn(order);
        Assert.assertEquals(orderService.findByClientId(anyLong()), order);
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
        when(orderDao.findById(anyLong())).thenReturn(order);
        orderService.updateOrder(order);
        verify(orderDao, atLeastOnce()).findById(anyLong());
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
        when(orderDao.findByClientId(anyLong())).thenReturn(order);
        Assert.assertEquals(orderService.isOrderNumberUnique(order.getId(), order.getId()), true);
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
