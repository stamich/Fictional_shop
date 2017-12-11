package pl.multishop.service;

import pl.multishop.model.UserProfile;

import java.util.List;

/**
 * Interface of service layer for user profile model.
 * @author Michał Stawarski
 */
public interface UserProfileService {

    UserProfile findById(int profileId);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
