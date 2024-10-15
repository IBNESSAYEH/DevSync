package com.youcode.DevSyncV1.service.implementation;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.repository.implementation.UserRepositoryImpl;
import com.youcode.DevSyncV1.service.UserService;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepositoryImpl userRepository;


    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }


    public User createUser(User user) {
        if (user.getJetonParJour() == null) {
            user.setJetonParJour("2");
        }
        if (user.getJetonParMois() == null) {
            user.setJetonParMois("1");
        }
        return userRepository.save(user);
    }
    public boolean updateUserJetonRemplacement(Long id) {
        return userRepository.updateUserJetonRemplacement(id);
    }
    public boolean updateUserJetonParMois(Long id) {
        return userRepository.updateUserJetonParMois(id);
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
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setJetonParJour("2");
            existingUser.setJetonParMois("1");
            existingUser.setManager(user.getManager());
            existingUser.setDateOfFirstReplacementOrder(user.getDateOfFirstReplacementOrder() != null ? user.getDateOfFirstReplacementOrder() : LocalDateTime.now());
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
