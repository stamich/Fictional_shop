package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Order;

import java.util.List;

/**
 * This class extends AbstractDao class and implements OrderDao interface
 * for creating methods used with order model.
 * @author Michał Stawarski
 */
@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

    /**
     * This method finds order.
     * @param orderId
     * @return getById(orderId)
     */
    @Override
    public Order findById(int orderId) {
        return getById(orderId);
    }

    /**
     * This method finds client.
     * @param clientId
     * @return (Order) criteria.uniqueResult()
     */
    @Override
    public Order findByClientId(String clientId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("clientId", clientId));
        return (Order) criteria.uniqueResult();
    }

    /**
     * This method saves order.
     * @param order
     */
    @Override
    public void saveOrder(Order order) {
        persistEntity(order);
    }

    /**
     * This method deletes order.
     * @param orderId
     */
    @Override
    public void delOrderById(int orderId) {
        Query query = getSession().createQuery("delete from Orders where orderId = :orderId");
        query.setInteger("orderId", orderId);
        query.executeUpdate();
    }

    /**
     * This method makes the list of all orders.
     * @return (List<Order>) criteria.list()
     */
    @Override
    public List<Order> findAllOrders() {
        Criteria criteria = createEntityCriteria();
        return (List<Order>) criteria.list();
    }
}
