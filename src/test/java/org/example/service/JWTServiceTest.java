package org.example.service;

import io.jsonwebtoken.Claims;
import org.example.ApplicationBootstrap;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RatingDao;
import org.example.repository.RoleDao;
import org.example.repository.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    private User user1;
    private Role role1;

    @Before
    public void SetUp(){
        user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("test");

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
        roleDao.delete(role1);
        userDao.delete(user1);
    }

    @Test
    public void generateTokenTest(){
        String token = jwtService.generateToken(user1);
        String[] array = token.split("\\.");
        assertNotNull(token);
        assertEquals(array.length, 3);
    }
    @Test
    public void decryptJwtTokenTest(){
        String token = jwtService.generateToken(user1);
        Claims claims = jwtService.decryptJwtToken(token);
        assertNotNull(claims);
        assertEquals(claims.getSubject(), user1.getUsername());
    }

}
