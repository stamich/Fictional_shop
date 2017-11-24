package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Orders;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Orders> implements OrderDao {

    @Override
    public Orders findById(int orderId) {
        return getById(orderId);
    }

    @Override
    public Orders findByClientId(String clientId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("clientId", clientId));
        return (Orders) criteria.uniqueResult();
    }

    @Override
    public void saveOrder(Orders orders) {
        persistEntity(orders);
    }

    @Override
    public void delOrderById(int orderId) {
        Query query = getSession().createQuery("delete from Orders where orderId = :orderId");
        query.setInteger("orderId", orderId);
        query.executeUpdate();
    }

    @Override
    public List<Orders> findAllOrders() {
        Criteria criteria = createEntityCriteria();
        return (List<Orders>) criteria.list();
    }
}
