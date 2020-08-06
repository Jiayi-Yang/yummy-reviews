package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User save(User user){
        return userDao.save(user);
    }
    public User update(User user) {
        return userDao.update(user);
    }
    public boolean delete(User user){
        return userDao.delete(user);
    }
    public List<User> getUsers(){
        return userDao.getUsers();
    }
    public User getBy(Long id) {
        return userDao.getBy(id);
    }
    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }
    public User getUSerByCredentials(String username, String password){
        return userDao.getUserByCredentials(username, password);
    }
}
