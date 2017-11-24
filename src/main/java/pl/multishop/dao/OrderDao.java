package pl.multishop.dao;

import pl.multishop.model.Orders;

import java.util.List;

public interface OrderDao {

    public Orders findById(int orderId);

    public Orders findByClientId(String clientId);

    public void saveOrder(Orders orders);

    public void delOrderById(int orderId);

    public List<Orders> findAllOrders();

}
