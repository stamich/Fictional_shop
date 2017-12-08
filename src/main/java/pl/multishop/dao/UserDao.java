package pl.multishop.dao;

import pl.multishop.model.User;

import java.util.List;

public interface UserDao {

    public User findById(int userId);
    public User findBySsO(String sSoId);
    public void saveUser(User user);
    public void deleteBySso(String sSoId);
    public List<User> findAllUsers();

}
