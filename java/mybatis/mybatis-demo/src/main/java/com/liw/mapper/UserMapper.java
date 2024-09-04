package com.liw.mapper;

import com.liw.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User select(String username, String password);
}
