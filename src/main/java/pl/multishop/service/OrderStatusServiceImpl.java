package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.OrderStatusDao;
import pl.multishop.model.OrderStatus;

import java.util.List;

@Transactional
@Service("orderStstusService")
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusDao dao;

    @Override
    public OrderStatus findByStatus(String status) {
        return dao.findByStatus(status);
    }

    @Override
    public OrderStatus findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<OrderStatus> findAll() {
        return dao.findAll();
    }
}
