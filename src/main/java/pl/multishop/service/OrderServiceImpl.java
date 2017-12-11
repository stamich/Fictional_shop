package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.OrderDao;
import pl.multishop.model.Orders;
import pl.multishop.model.Product;

import java.util.List;

/**
 * This class implements business logic for order model of data.
 * @author Michal Stawarski
 */
@Service("orderService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * This method finds order by its id.
     * @param orderId
     * @return orderDao.findById(orderId)
     */
    @Override
    public Orders findById(int orderId) {
        return orderDao.findById(orderId);
    }

    /**
     * This method finds client id.
     * @param clientId
     * @return orderDao.findByClientId(clientId)
     */
    @Override
    public Orders findByClientId(String clientId) {
        return orderDao.findByClientId(clientId);
    }

    /**
     * This method saves order.
     * @param orders
     */
    @Override
    public void saveOrder(Orders orders) {
        orderDao.saveOrder(orders);
    }

    /**
     * This method updates order.
     * @param orders
     */
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

    /**
     * This method deletes order.
     * @param orderId
     */
    @Override
    public void delOrderById(int orderId) {
        orderDao.delOrderById(orderId);
    }

    /**
     * This method makes the list of all orders.
     * @return orderDao.findAllOrders()
     */
    @Override
    public List<Orders> findAllOrders() {
        return orderDao.findAllOrders();
    }

    /**
     * This method verifies if order number is unique.
     * @param orderId
     * @param clientId
     * @return (orders == null || (orderId != null) && orders.getOrderId() == orderId)
     */
    @Override
    public boolean isOrderNumberUnique(Integer orderId, String clientId){
        Orders orders = findByClientId(clientId);
        return (orders == null || (orderId != null) && orders.getOrderId() == orderId);
    }
}
