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

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;
    private Role role1;

    @Before
    public void SetUp(){
        role1 = new Role();
        role1.setName("SSR");
        role1.setAllowedCreate(true);
        role1.setAllowedDelete(true);
        role1.setAllowedRead(true);
        role1.setAllowedResource("/depts,/departments,/employees,/ems,/acnts,/accounts");
        role1.setAllowedUpdate(true);
        role1 = roleDao.save(role1);
    }

    @After
    public void tearDown(){
        roleDao.delete(role1);
    }

    @Test
    public void getRolesTest(){
        List<Role> roles = roleDao.getRoles();
        int expectedNumberOfRole = 4;
        assertEquals(expectedNumberOfRole, roles.size());
    }
}
