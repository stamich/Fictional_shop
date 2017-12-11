package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.User;

import java.util.List;

/**
 * This class extends AbstractDao class and implements UserDao interface
 * for creating methods used with user model.
 * @author Michał Stawarski
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

    /**
     * This method finds user by its unique id.
     * @param userId
     * @return user
     */
    @Override
    public User findById(int userId) {
        User user = getByKey(userId);
        if(user != null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    /**
     * This method finds user by its unique sso.
     * @param sSoId
     * @return user
     */
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

    /**
     * This method saves user.
     * @param user
     */
    @Override
    public void saveUser(User user) {
        persistEntity(user);
    }

    /**
     * This method deletes user.
     * @param sSoId
     */
    @Override
    public void deleteBySso(String sSoId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("sSoId", sSoId));
        User user = (User)criteria.uniqueResult();
        deleteEntity(user);
    }

    /**
     * This method makes the list of all users.
     * @return users
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        return users;
    }
}
