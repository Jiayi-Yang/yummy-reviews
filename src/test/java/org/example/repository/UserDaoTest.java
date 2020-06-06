package org.example.repository;

import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    private UserDao userJDBCDao;

    @Before
    public void setUp(){
        userJDBCDao = new UserDao();
    }
    @After
    public void tearDown(){
        userJDBCDao=null;
    }
    @Test
    public void getUsersTest(){
        assertEquals(1,userJDBCDao.getUsers().size());
    }
}
