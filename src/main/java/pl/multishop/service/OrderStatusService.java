package pl.multishop.service;

import pl.multishop.model.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    OrderStatus findByStatus(String status);
    OrderStatus findById(Long id);
    List<OrderStatus> findAll();
}
