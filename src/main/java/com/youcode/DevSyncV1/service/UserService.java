package com.youcode.DevSyncV1.service;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.repository.UserRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserService {

    private UserRepository userRepository;


    public UserService(EntityManager entityManager) {
        this.userRepository = new UserRepository(entityManager);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user, Long id) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            existingUser.setId(id);
            existingUser.setUsername(user.getUsername());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }
        return null;
    }

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
