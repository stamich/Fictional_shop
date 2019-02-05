package pl.multishop.service;

import pl.multishop.model.Order;

import java.util.List;

/**
 * Interface of service layer for order model.
 * @author Michał Stawarski
 */
public interface OrderService {

    Order findById(Long orderId);
    Order findByClientId(Long clientId);
    void saveOrder(Order order);
    void updateOrder(Order order);
    void delOrderById(int orderId);
    List<Order> findAllOrders();
    List<Order> findAllOrders(Long clientId);
    boolean isOrderNumberUnique(Long orderId, Long clientId);
}