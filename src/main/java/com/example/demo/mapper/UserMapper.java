package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(@Param("id") Long id);

    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    // keyProperty和keyColumn分别指出JavaBean的属性和数据库的主键列名
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO users (username) VALUES (#{user.username})")
    void create(@Param("user") User user);

    @Update("UPDATE users SET username = #{user.username} WHERE id = #{id}")
    void update(@Param("id") Long id, @Param("user") User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
}
