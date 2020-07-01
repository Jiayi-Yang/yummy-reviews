package org.example.repository;

import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    private UserDao userDao;
    private User u1;

    @Before
    public void SetUp(){
        userDao = new UserDaoImpl();
        u1 = new User();
        u1.setUserId(0);
        u1.setEmail("test@gmail.com");
        u1.setPassword("password");
        u1.setUsername("test");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        u1.setCreatedOn(timestamp);
        u1=userDao.save(u1);
    }
    @After
    public void tearDown(){
        userDao.delete(u1);
    }

    @Test
    public void getUsersTest(){
        List<User> users = userDao.getUsers();
        int expectedNumberOfUser = 1;
        assertEquals(expectedNumberOfUser, users.size());
    }
}
