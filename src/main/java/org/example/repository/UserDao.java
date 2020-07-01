package org.example.repository;

import org.example.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public interface UserDao{
    User save(User user);
    User update(User user);
    boolean delete(User user);
    List<User> getUsers();
    User getBy(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}