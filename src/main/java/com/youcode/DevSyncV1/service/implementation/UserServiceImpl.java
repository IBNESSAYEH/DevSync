package com.youcode.DevSyncV1.service.implementation;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.repository.implementation.UserRepositoryImpl;
import com.youcode.DevSyncV1.service.UserService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepositoryImpl userRepository;


    public UserServiceImpl(EntityManager entityManager) {
        this.userRepository = new UserRepositoryImpl(entityManager);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
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

    public User getUser(Long id) {
        return userRepository.findById(id);
    }
}
