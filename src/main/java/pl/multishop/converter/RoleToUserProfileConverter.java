package pl.multishop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.multishop.model.UserProfile;
import pl.multishop.service.UserProfileService;

/**
 * This class converts profil of user and implements parametric
 * Spring interface called Converter.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>{

    @Autowired
    UserProfileService userProfileService;

    /**
     * This method gets UserProfile by Id
     * @param element
     * @return userProfile
     */
    @Override
    public UserProfile convert(Object element) {
        Integer profileId = Integer.parseInt((String) element);
        UserProfile userProfile = userProfileService.findById(profileId);
        return userProfile;
    }
}
