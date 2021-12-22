package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class UserService {

    @Autowired
    private UserDao userDao;

//    @Value("${demo.test.value}")
//    private String value;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public User getUserByStringId(String id) {
        return userDao.getUserById(Long.parseLong(id));
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
