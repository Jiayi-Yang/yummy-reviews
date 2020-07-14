package org.example.service;

import io.jsonwebtoken.Claims;
import org.example.ApplicationBootstrap;
import org.example.model.User;
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

    private User user1;
    @Before
    public void SetUp(){
        user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("test");
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
