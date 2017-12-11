package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.UserDao;
import pl.multishop.model.User;

import java.util.List;

/**
 * This class implements business logic for user profile model of data.
 * @author Michal Stawarski
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This method finds user by id.
     * @param userId
     * @return userDao.findById(userId)
     */
    @Override
    public User findById(int userId) {
        return userDao.findById(userId);
    }

    /**
     * This method finds user by its unique sso.
     * @param sSoId
     * @return user
     */
    @Override
    public User findBySSO(String sSoId) {
        User user = userDao.findBySsO(sSoId);
        return user;
    }

    /**
     * This method saves user
     * @param user
     */
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    /**
     * This method updates user
     * @param user
     */
    @Override
    public void updateUser(User user) {
        User entity = userDao.findById(user.getUserId());
        if(entity != null){
            entity.setsSoId(user.getsSoId());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }

    /**
     * This method deletes user
     * @param sSoId
     */
    @Override
    public void deleteUserBySSO(String sSoId) {
        userDao.deleteBySso(sSoId);
    }

    /**
     * This method makes the list of all users.
     * @return userDao.findAllUsers()
     */
    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    /**
     * This method verifies if user sso is unique.
     * @param userId
     * @param sSoId
     * @return ( user == null || ((userId != null) && (user.getUserId() == userId)))
     */
    @Override
    public boolean isUserSSOUnique(Integer userId, String sSoId) {
        User user = findBySSO(sSoId);
        return ( user == null || ((userId != null) && (user.getUserId() == userId)));
    }
}
