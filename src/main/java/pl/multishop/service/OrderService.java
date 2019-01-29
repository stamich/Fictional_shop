package pl.multishop.service;

import pl.multishop.model.Order;

import java.util.List;

/**
 * Interface of service layer for order model.
 * @author Michał Stawarski
 */
public interface OrderService {

    Order findById(int orderId);

    Order findByClientId(String clientId);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void delOrderById(int orderId);

    List<Order> findAllOrders();

    boolean isOrderNumberUnique(Integer orderId, String clientId);
}