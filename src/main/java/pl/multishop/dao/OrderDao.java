package pl.multishop.dao;

import pl.multishop.model.Order;

import java.util.List;

/**
 * Interface of DAO layer for order model.
 * @author Michał Stawarski
 */
public interface OrderDao {

    Order findById(Long orderId);
    Order findByClientId(Long clientId);
    void saveOrder(Order order);
    void delOrderById(int orderId);
    List<Order> findAllOrders();
    List<Order> findAllOrders(Long clientId);
}
