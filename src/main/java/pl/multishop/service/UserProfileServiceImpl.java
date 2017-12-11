package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.UserProfileDao;
import pl.multishop.model.UserProfile;

import java.util.List;

/**
 * This class implements business logic for user profile model of data.
 * @author Michal Stawarski
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao userProfileDao;

    /**
     * This method finds user profile by id.
     * @param profileId
     * @return
     */
    @Override
    public UserProfile findById(int profileId) {
        return userProfileDao.findById(profileId);
    }

    /**
     * This method finds user profile by its type.
     * @param type
     * @return
     */
    @Override
    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    /**
     * This method makes the list of all user profiles.
     * @return
     */
    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }
}
