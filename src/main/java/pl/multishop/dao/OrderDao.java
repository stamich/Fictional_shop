package pl.multishop.dao;

import pl.multishop.model.Orders;

import java.util.List;

/**
 * Interface of DAO layer for order model.
 * @author Michał Stawarski
 */
public interface OrderDao {

    Orders findById(int orderId);

    Orders findByClientId(String clientId);

    void saveOrder(Orders orders);

    void delOrderById(int orderId);

    List<Orders> findAllOrders();

}
