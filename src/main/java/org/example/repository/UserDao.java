package org.example.repository;

import org.example.model.User;
import java.util.List;

public interface UserDao{
    User save(User user);
    User update(User user);
    boolean delete(User user);
    List<User> getUsers();
    User getBy(Long id);
    User getUserEagerBy(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}