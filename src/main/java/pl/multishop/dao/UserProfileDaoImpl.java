package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.UserProfile;

import java.util.List;

/**
 * This class extends AbstractDao class and implements UserProfileDao interface
 * for creating methods used with user profile model.
 * @author Michał Stawarski
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao{

    /**
     * This method finds user profile by its type.
     * @param type
     * @return (UserProfile) criteria.uniqueResult()
     */
    @Override
    public UserProfile findByType(String type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        return (UserProfile) criteria.uniqueResult();
    }

    /**
     * This method finds user profile by its unique id.
     * @param profileId
     * @return getByKey(profileId)
     */
    @Override
    public UserProfile findById(int profileId) {
        return getByKey(profileId);
    }

    /**
     * This method makes the list of all user profiles.
     * @return (List<UserProfile>)criteria.list()
     */
    @Override
    public List<UserProfile> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("type"));
        return (List<UserProfile>)criteria.list();
    }
}
