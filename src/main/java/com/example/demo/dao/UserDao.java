package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    User getUserById(@Param("id") Long id);

    List<User> getAllUsers();

    void create(@Param("user") User user);

    void update(@Param("id") Long id, @Param("user") User user);

    void deleteById(@Param("id") Long id);
}
