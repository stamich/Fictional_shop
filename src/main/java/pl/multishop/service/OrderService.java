package pl.multishop.service;

import pl.multishop.model.Orders;

import java.util.List;

/**
 * Created by michal on 06.05.17.
 */
public interface OrderService {

    public Orders findById(int orderId);

    public Orders findByClientId(String clientId);

    public void saveOrder(Orders orders);

    public void updateOrder(Orders orders);

    public void delOrderById(int orderId);

    public List<Orders> findAllOrders();

    public boolean isOrderNumberUnique(Integer orderId, String clientId);
}