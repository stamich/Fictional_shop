package pl.multishop.dao;

import pl.multishop.model.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    OrderStatus findByStatus(String status);
    OrderStatus findById(Long id);
    List<OrderStatus> findAll();
}
