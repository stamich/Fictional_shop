package pl.multishop.service;

import pl.multishop.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile findById(int profileId);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
