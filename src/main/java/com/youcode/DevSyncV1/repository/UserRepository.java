package com.youcode.DevSyncV1.repository;

import com.youcode.DevSyncV1.entities.User;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public interface UserRepository {

     User save(User user);

     User findById(Long id);

     List<User> findAll();

     void delete(User user);

     User findByUsername(String username);
     boolean updateUserJetonRemplacement(Long id);
     boolean updateUserJetonParMois(Long id);
}
