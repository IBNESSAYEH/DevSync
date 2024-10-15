package com.youcode.DevSyncV1.repository.implementation;

import com.youcode.DevSyncV1.entities.Task;
import com.youcode.DevSyncV1.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private EntityManagerFactory emf;

    public TaskRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("myJPAUnit");
    }

    @Override
    public Task getTaskById(long taskId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Task.class, taskId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Task> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Task save(Task task) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (task.getId() == null) {
                entityManager.persist(task);
            } else {
                task = entityManager.merge(task);
            }
            transaction.commit();
            return task;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Task task) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (!entityManager.contains(task)) {
                task = entityManager.merge(task);
            }
            entityManager.remove(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Task t WHERE t.createdBy = :username", Task.class)
                    .setParameter("username", username).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Task> findTasksByTag(String tagName) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Task t JOIN t.tags tg WHERE tg.name = :tagName", Task.class)
                    .setParameter("tagName", tagName).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public boolean updateReplacementOrder(long taskId) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            int updateReplacementOrder = entityManager.createQuery("UPDATE Task t SET t.replacementOrder = true WHERE t.id = :taskId")
                    .setParameter("taskId", taskId)
                    .executeUpdate();
            transaction.commit();
            return updateReplacementOrder > 0;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    public boolean AcceptReplacementOrder(long taskId) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            int updateReplacementOrder = entityManager.createQuery("UPDATE Task t SET t.replacementPossibility = false WHERE t.id = :taskId")
                    .setParameter("taskId", taskId)
                    .executeUpdate();
            transaction.commit();
            return updateReplacementOrder > 0;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
}