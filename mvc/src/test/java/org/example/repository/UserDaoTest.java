package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Rating;
import org.example.model.Role;
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
    @Autowired
    private RoleDao roleDao;

    private User user1;
    private Rating rating1;
    private Rating rating2;
    private Role role1;
    private Role role2;
    private String email = "test@gmail.com";

    @Before
    public void SetUp(){

        user1 = new User();
        user1.setEmail(email);
        user1.setPassword("password");
        user1.setUsername("test");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user1.setCreatedOn(timestamp);
        user1=userDao.save(user1);

        rating1 = new Rating();
        rating1.setRatingScore(9);
        rating1.setGoodReview("yummy");
        rating1.setBadReview("expensive");
        rating1.setUser(user1);
        rating1=ratingDao.save(rating1);

        rating2 = new Rating();
        rating2.setRatingScore(8);
        rating2.setGoodReview("yummy");
        rating2.setBadReview("too spicy");
        rating2.setUser(user1);
        rating2=ratingDao.save(rating2);

        role1 = new Role();
        role1.setName("VP");
        role1.setAllowedCreate(true);
        role1.setAllowedDelete(true);
        role1.setAllowedRead(true);
        role1.setAllowedResource("/depts,/departments,/employees,/ems,/acnts,/accounts");
        role1.setAllowedUpdate(true);
        role1 = roleDao.save(role1);
        user1.addRole(role1);
        user1 = userDao.save(user1);

    }
    @After
    public void tearDown(){
        ratingDao.delete(rating1);
        ratingDao.delete(rating2);

        roleDao.delete(role1);
        userDao.delete(user1);
    }

    @Test
    public void getUsersTest(){
        List<User> users = userDao.getUsers();
        int expectedNumberOfUser = 1;
        assertEquals(expectedNumberOfUser, users.size());
    }

    @Test
    public void getUserEagerByTest(){
        User user = userDao.getUserEagerBy(user1.getUserId());
        assertNotNull(user);
        assertEquals(user.getUsername(), user1.getUsername());
        assertTrue(user.getRatings().size()>0);
    }

    @Test
    public void getByTest(){
        User user = userDao.getBy(user1.getUserId());
        assertNotNull(user);
        assertEquals(user.getUsername(), user1.getUsername());
        assertEquals(user.getEmail(), user1.getEmail());
    }

    @Test
    public void updateUserTest(){
        User user = userDao.getBy(user1.getUserId());
        assertNotNull(user);
        user.setUsername("newtest");
        user= userDao.update(user);
        assertNotNull(user);
        assertEquals(user.getUsername(), "newtest");
    }

    @Test
    public void getUserByUsernameTest(){
        User user = userDao.getBy(user1.getUserId());
        assertEquals(user.getUsername(), user1.getUsername());
    }

    @Test
    public void getUserWithRole(){
        User user = userDao.getUserByEmail(email);
        assertEquals(user.getRoles().size(),1);
    }

    @Test
    public void getUserByCredentialsTest(){
        User user = userDao.getUserByCredentials(user1.getUsername(), user1.getPassword());
        assertEquals(user.getUsername(), user1.getUsername());
        assertEquals(user.getEmail(), user1.getEmail());
    }
}
