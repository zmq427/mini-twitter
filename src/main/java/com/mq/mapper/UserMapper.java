package com.mq.mapper;

import com.mq.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User select(@Param("username") String username, @Param("password") String password);

    User selectByUsername(String username);

    void addUser(@Param("username") String username, @Param("password") String password);
}
