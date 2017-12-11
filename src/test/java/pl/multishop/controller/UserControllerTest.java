package pl.multishop.controller;

import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.multishop.controller.UserController;
import pl.multishop.model.User;
import pl.multishop.service.UserProfileService;
import pl.multishop.service.UserService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    UserProfileService userProfileService;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    UserController userController;

    @Spy
    List<User> users = new ArrayList<User>();

    @Spy
    ModelMap modelMap;

    @Mock
    BindingResult bindingResult;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        users = getUserList();
    }


    @Test
    public void listUsers(){
        when(userService.findAllUsers()).thenReturn(users);
        Assert.assertEquals(userController.listUsers(modelMap), "usersList");
        Assert.assertNotNull(modelMap.get("loggedinuser"));
        Assert.assertEquals(modelMap.get("users"), users);
        verify(userService, atLeastOnce()).findAllUsers();
    }

    @Test
    public void newUser(){
        Assert.assertEquals(userController.newUser(modelMap), "registration");
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertFalse((Boolean)modelMap.get("edit"));
        Assert.assertNotNull(modelMap.get("loggedinuser"));
        Assert.assertEquals(((User)modelMap.get("user")).getsSoId(), 0);
    }

    @Test
    public void deleteUser(){
        doNothing().when(userService).deleteUserBySSO(anyString());
        Assert.assertEquals(userController.deleteUser("123"), "redirect:/listUsers");
    }

    public List<User> getUserList(){

        User u1 = new User();

        u1.setUserId(1);
        u1.setsSoId("2532");
        u1.setPassword("password");
        u1.setFirstName("Jan");
        u1.setLastName("Kowalski");
        u1.setEmail("jk@home.pl");

        User u2 = new User();

        u2.setUserId(2);
        u2.setsSoId("1212");
        u2.setPassword("password2");
        u2.setFirstName("Maria");
        u2.setLastName("Nowak");
        u2.setEmail("maria.n@op.pl");

        users.add(u1);
        users.add(u2);
        return users;
    }

}
