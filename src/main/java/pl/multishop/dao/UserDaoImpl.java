package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.User;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

    @Override
    public User findById(int userId) {
        User user = getByKey(userId);
        if(user != null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public User findBySsO(String sSoId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("sSoId", sSoId));
        User user = (User)criteria.uniqueResult();
        if(user != null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        persistEntity(user);
    }

    @Override
    public void deleteBySso(String sSoId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("sSoId", sSoId));
        User user = (User)criteria.uniqueResult();
        deleteEntity(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        return users;
    }
}
