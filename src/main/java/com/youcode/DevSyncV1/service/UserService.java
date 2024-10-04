package com.youcode.DevSyncV1.service;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserService
{

    private UserRepository userRepository = new UserRepository();


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
