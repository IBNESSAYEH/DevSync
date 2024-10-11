package com.youcode.DevSyncV1.repository.implementation;

import com.youcode.DevSyncV1.entities.Task;
import com.youcode.DevSyncV1.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private EntityManager entityManager;

    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Task getTaskById(long taskId) {
        return entityManager.find(Task.class, taskId);
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        System.out.println("Retrieved tasks: " + tasks);
        return tasks;
    }

    @Override
    public Task save(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        if (task.getId() == null) {
            entityManager.persist(task);
        } else {
            task = entityManager.merge(task);
        }

        transaction.commit();
        return task;
    }

    @Override
    public void delete(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();


        if (!transaction.isActive()) {
            transaction.begin();
        }

        if (!entityManager.contains(task)) {
            task = entityManager.merge(task);
        }
        entityManager.remove(task);

        transaction.commit();
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.createdBy = :username", Task.class)
                .setParameter("username", username).getResultList();
    }

    @Override
    public List<Task> findTasksByTag(String tagName) {
        return entityManager.createQuery("SELECT t FROM Task t JOIN t.tags tg WHERE tg.name = :tagName", Task.class)
                .setParameter("tagName", tagName).getResultList();
    }
}
