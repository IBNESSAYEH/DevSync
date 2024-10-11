package com.youcode.DevSyncV1.repository.implementation;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.repository.UserRepository;
import jakarta.persistence.*;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager em;


    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public User save(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            user = em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }



    public User findById(Long id) {
        return em.find(User.class, id);

    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
    public User findByEmailAndPassword(String email, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void delete(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            user = em.contains(user) ? user : em.merge(user);
            em.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return result;
    }
}
