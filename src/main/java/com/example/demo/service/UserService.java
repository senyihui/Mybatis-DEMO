package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public String updateUser(Long userId, User user) {
        if (getUserById(userId) != null) {
            userDao.update(userId, user);
            return "update user: " + user.getUsername();
        } else {
            return "not exist";
        }
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
