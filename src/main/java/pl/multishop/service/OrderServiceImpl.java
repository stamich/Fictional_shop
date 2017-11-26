package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.OrderDao;
import pl.multishop.model.Orders;

import java.util.List;

/**
 * Created by michal on 06.05.17.
 */

@Service("orderService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Orders findById(int orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public Orders findByClientId(String clientId) {
        return orderDao.findByClientId(clientId);
    }

    @Override
    public void saveOrder(Orders orders) {
        orderDao.saveOrder(orders);
    }

    @Override
    public void updateOrder(Orders orders) {
        Orders entity = orderDao.findById(orders.getOrderId());
        if(entity!=null) {
            //entity.setOrderId(orders.getOrderId());//updatable = false
            //entity.setClientId(orders.getClientId());//updatable = false
            //entity.setProductId(orders.getProductId());//updatable = false
            entity.setAmount(orders.getAmount());
            entity.setOrderDate(orders.getOrderDate());
            entity.setOrderStatus(orders.getOrderStatus());
        }
    }

    @Override
    public void delOrderById(int orderId) {
        orderDao.delOrderById(orderId);
    }

    @Override
    public List<Orders> findAllOrders() {
        return orderDao.findAllOrders();
    }
}
