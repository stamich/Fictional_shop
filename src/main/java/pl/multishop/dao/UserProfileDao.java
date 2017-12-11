package pl.multishop.dao;

import pl.multishop.model.UserProfile;

import java.util.List;

/**
 * Interface of DAO layer for user profile model.
 * @author Michał Stawarski
 */
public interface UserProfileDao {

    UserProfile findByType(String type);
    UserProfile findById(int profileId);
    List<UserProfile> findAll();
}
