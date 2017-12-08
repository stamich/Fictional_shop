package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.UserProfileDao;
import pl.multishop.model.UserProfile;

import java.util.List;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao userProfileDao;

    @Override
    public UserProfile findById(int profileId) {
        return userProfileDao.findById(profileId);
    }

    @Override
    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }
}
