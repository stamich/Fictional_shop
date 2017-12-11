package pl.multishop.service;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.dao.UserDao;
import pl.multishop.model.User;
import pl.multishop.service.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService;

    @Spy
    List<User> users = new ArrayList<User>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        users = getUserList();
    }

    @Test
    public void findById(){
        User user = users.get(0);
        when(userDao.findById(anyInt())).thenReturn(user);
        Assert.assertEquals(userService.findById(user.getUserId()), user);
    }

    @Test
    public void findBySSO(){
        User user = users.get(0);
        when(userDao.findBySsO(anyString())).thenReturn(user);
        Assert.assertEquals(userService.findBySSO(user.getsSoId()), user);
    }

    @Test
    public void saveUser(){
        doNothing().when(userDao).saveUser(any(User.class));
        userService.saveUser(any(User.class));
        verify(userDao, atLeastOnce()).saveUser(any(User.class));
    }

    @Test
    public void updateUser(){
        User user = users.get(0);
        when(userDao.findById(anyInt())).thenReturn(user);
        userService.updateUser(user);
        verify(userDao, atLeastOnce()).findById(anyInt());
    }

    @Test
    public void deleteUserBySSO(){
        doNothing().when(userDao).deleteBySso(anyString());
        userService.deleteUserBySSO(anyString());
        verify(userDao, atLeastOnce()).deleteBySso(anyString());
    }

    @Test
    public void findAllUsers(){
        when(userDao.findAllUsers()).thenReturn(users);
        Assert.assertEquals(userService.findAllUsers(), users);
    }

    @Test
    public void isUserSSOUnique(){
        User user = users.get(0);
        when(userDao.findBySsO(anyString())).thenReturn(user);
        Assert.assertEquals(userService.isUserSSOUnique(user.getUserId(), user.getsSoId()), true);
    }

    public List<User> getUserList(){

        User u1 = new User();

        u1.setUserId(1);
        u1.setsSoId("2432");
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
