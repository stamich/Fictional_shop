package pl.multishop.dao;

import pl.multishop.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

    public UserProfile findByType(String type);
    public UserProfile findById(int profileId);
    public List<UserProfile> findAll();
}
