package com.youcode.DevSyncV1.service;

import com.youcode.DevSyncV1.entities.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
}
