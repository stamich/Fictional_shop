package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Orders;

import java.util.List;

/**
 * This class extends AbstractDao class and implements OrderDao interface
 * for creating methods used with order model.
 * @author Michał Stawarski
 */
@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Orders> implements OrderDao {

    /**
     * This method finds order.
     * @param orderId
     * @return getById(orderId)
     */
    @Override
    public Orders findById(int orderId) {
        return getById(orderId);
    }

    /**
     * This method finds client.
     * @param clientId
     * @return (Orders) criteria.uniqueResult()
     */
    @Override
    public Orders findByClientId(String clientId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("clientId", clientId));
        return (Orders) criteria.uniqueResult();
    }

    /**
     * This method saves order.
     * @param orders
     */
    @Override
    public void saveOrder(Orders orders) {
        persistEntity(orders);
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
     * @return (List<Orders>) criteria.list()
     */
    @Override
    public List<Orders> findAllOrders() {
        Criteria criteria = createEntityCriteria();
        return (List<Orders>) criteria.list();
    }
}
