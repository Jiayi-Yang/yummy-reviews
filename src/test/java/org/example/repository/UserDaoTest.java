package org.example.repository;

import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    UserDao userJDBCDao = new UserDao();
    //private UserDao userJDBCDao;

    @Before
    public void setUp(){
//        User user = new User();
//        user.setUserName("testUser");
//        user.setPassword("123xxx");
//        user.save(user);
       // userJDBCDao = new UserDao();

    }
    @After
    public void tearDown(){
//        user.delete();
//        userJDBCDao=null;
    }

    @Test
    public void getUsersTest(){
        assertEquals(3,userJDBCDao.getUsers().size());
    }
}
