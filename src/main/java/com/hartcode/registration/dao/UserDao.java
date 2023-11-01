package com.hartcode.registration.dao;

import com.hartcode.registration.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
}
