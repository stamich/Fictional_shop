package pl.multishop.dao;

import pl.multishop.model.Orders;
import pl.multishop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderDao {

    public Orders findById(int orderId);

    public Orders findByClientId(String clientId);

    public void saveOrder(Orders orders);

    public void delOrderById(int orderId);

    public List<Orders> findAllOrders();

    public Set<Product> findProductsByFilter(Map<String, List<String>> filterParams);

}
