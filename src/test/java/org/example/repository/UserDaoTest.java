package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Rating;
import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RatingDao ratingDao;
    private User u1;
    private Rating r1;
    private Rating r2;

    @Before
    public void SetUp(){

        u1 = new User();
        u1.setEmail("test@gmail.com");
        u1.setPassword("password");
        u1.setUsername("test");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        u1.setCreatedOn(timestamp);
        u1=userDao.save(u1);

        r1 = new Rating();
        r1.setRatingScore(9);
        r1.setGoodReview("yummy");
        r1.setBadReview("expensive");
        r1.setUser(u1);
        ratingDao.save(r1);

        r2 = new Rating();
        r2.setRatingScore(8);
        r2.setGoodReview("yummy");
        r2.setBadReview("too spicy");
        r2.setUser(u1);
        ratingDao.save(r2);

    }
    @After
    public void tearDown(){
        ratingDao.delete(r1);
        ratingDao.delete(r2);
        userDao.delete(u1);
    }

    @Test
    public void getUsersTest(){
        List<User> users = userDao.getUsers();
        int expectedNumberOfUser = 1;
        assertEquals(expectedNumberOfUser, users.size());
    }

    @Test
    public void getUserEagerByTest(){
        User user = userDao.getUserEagerBy(u1.getUserId());
        assertNotNull(user);
        assertEquals(user.getUsername(), u1.getUsername());
        assertTrue(user.getRatings().size()>0);
    }

    @Test
    public void getByTest(){
        User user = userDao.getBy(u1.getUserId());
        assertNotNull(user);
        assertEquals(user.getUsername(), u1.getUsername());
        assertEquals(user.getEmail(), u1.getEmail());
    }

    @Test
    public void updateUserTest(){
        User user1 = userDao.getBy(u1.getUserId());
        assertNotNull(user1);
        user1.setUsername("newtest");
        user1= userDao.update(user1);
        assertNotNull(user1);
        assertEquals(user1.getUsername(), "newtest");
    }

    @Test
    public void getUserByUsernameTest(){
        User user = userDao.getBy(u1.getUserId());
        assertEquals(user.getUsername(), u1.getUsername());
    }
}
