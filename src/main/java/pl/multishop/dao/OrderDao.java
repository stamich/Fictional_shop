package pl.multishop.dao;

import pl.multishop.model.Order;

import java.util.List;

/**
 * Interface of DAO layer for order model.
 * @author Michał Stawarski
 */
public interface OrderDao {

    Order findById(int orderId);

    Order findByClientId(String clientId);

    void saveOrder(Order order);

    void delOrderById(int orderId);

    List<Order> findAllOrders();

}
