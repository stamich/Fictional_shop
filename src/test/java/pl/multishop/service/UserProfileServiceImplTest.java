package pl.multishop.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.dao.UserProfileDao;
import pl.multishop.model.User;
import pl.multishop.model.UserProfile;
import pl.multishop.service.UserProfileServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class UserProfileServiceImplTest {

    @Mock
    UserProfileDao userProfileDao;

    @InjectMocks
    UserProfileServiceImpl userProfileService;

    @Spy
    List<User> users = new ArrayList<User>();

    @Spy
    List<UserProfile> userProfiles = new ArrayList<UserProfile>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userProfiles = getUserProfileList();
    }

    @Test
    public void findById(){
        UserProfile userProfile = userProfiles.get(0);
        when(userProfileDao.findById(anyInt())).thenReturn(userProfile);
        Assert.assertEquals(userProfileService.findById(userProfile.getProfileId()), userProfile);
    }

    @Test
    public void findByType(){
        UserProfile userProfile = userProfiles.get(0);
        when(userProfileDao.findByType(anyString())).thenReturn(userProfile);
        Assert.assertEquals(userProfileService.findByType(userProfile.getType()), userProfile);
    }

    @Test
    public void findAll(){
        when(userProfileDao.findAll()).thenReturn(userProfiles);
        Assert.assertEquals(userProfileService.findAll(), userProfiles);
    }

    public List<UserProfile> getUserProfileList(){

        UserProfile up1 = new UserProfile();

        up1.setProfileId(1);
        up1.setType("DBA");

        UserProfile up2 = new UserProfile();

        up2.setProfileId(2);
        up2.setType("ADMIN");

        userProfiles.add(up1);
        userProfiles.add(up2);
        return userProfiles;
    }
}
