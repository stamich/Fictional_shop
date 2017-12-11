package pl.multishop.dao;

import pl.multishop.model.User;

import java.util.List;

/**
 * Interface of DAO layer for user model.
 * @author Michał Stawarski
 */
public interface UserDao {

    User findById(int userId);
    User findBySsO(String sSoId);
    void saveUser(User user);
    void deleteBySso(String sSoId);
    List<User> findAllUsers();

}
