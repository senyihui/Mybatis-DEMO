package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public void createUser(User user) {
        userMapper.create(user);
    }

    public String updateUser(Long userId, User user) {
        if (getUserById(userId) != null) {
            userMapper.update(userId, user);
            return "update user: " + user.getUsername();
        } else {
            return "not exist";
        }
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}
