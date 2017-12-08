package pl.multishop.service;

import pl.multishop.model.User;

import java.util.List;

public interface UserService {

    User findById(int userId);

    User findBySSO(String sSoId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sSoId);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer userId, String sSoId);

}
