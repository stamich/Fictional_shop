package pl.multishop.service;

import pl.multishop.model.Orders;

import java.util.List;

/**
 * Interface of service layer for order model.
 * @author Michał Stawarski
 */
public interface OrderService {

    Orders findById(int orderId);

    Orders findByClientId(String clientId);

    void saveOrder(Orders orders);

    void updateOrder(Orders orders);

    void delOrderById(int orderId);

    List<Orders> findAllOrders();

    boolean isOrderNumberUnique(Integer orderId, String clientId);
}