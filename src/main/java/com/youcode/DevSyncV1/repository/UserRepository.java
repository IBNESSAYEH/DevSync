package com.youcode.DevSyncV1.repository;



import com.youcode.DevSyncV1.entities.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;

public class UserRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJPAUnit");

        public User save(User user) {
            EntityManager entityManager = emf.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            entityManager.close();
            return user;
        }


        public User findById(Long id) {
            EntityManager entityManager = emf.createEntityManager();
            return entityManager.find(User.class, id);
        }

        public List<User> findAll() {
            EntityManager entityManager = emf.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> results = query.getResultList();
            entityManager.close();
            return results;
        }



        public void delete (User user) {
            EntityManager entityManager = emf.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
            transaction.commit();
            entityManager.close();
        }

       public User findByUsername(String username){
            EntityManager entityManager = emf.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User result = query.getSingleResult();
            entityManager.close();
            return result;
       }
    }