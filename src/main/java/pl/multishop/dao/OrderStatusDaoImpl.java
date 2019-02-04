package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.OrderStatus;

import java.util.List;

@Repository("orderStatusDao")
public class OrderStatusDaoImpl extends AbstractDao<Long, OrderStatus> implements OrderStatusDao {

    @Override
    public OrderStatus findByStatus(String status) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("status", status));
        return (OrderStatus) criteria.uniqueResult();
    }

    @Override
    public OrderStatus findById(Long id) {
        return getByKey(id);
    }

    @Override
    public List<OrderStatus> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("status"));
        return (List<OrderStatus>)criteria.list();
    }
}
