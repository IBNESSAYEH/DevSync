package com.youcode.DevSyncV1.service.implementation;

import com.youcode.DevSyncV1.entities.Task;
import com.youcode.DevSyncV1.repository.implementation.TaskRepositoryImpl;
import com.youcode.DevSyncV1.service.TaskService;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskRepositoryImpl taskRepository;

    public TaskServiceImpl(EntityManager entityManager) {
        this.taskRepository = new TaskRepositoryImpl(entityManager);
    }

    @Override
    public Task getTaskById(long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        validateTaskCreation(task);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, long taskId) {
        Task existingTask = taskRepository.getTaskById(taskId);
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setTags(task.getTags());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    @Override
    public void deleteTask(long taskId) {
        Task task = taskRepository.getTaskById(taskId);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        return taskRepository.findTasksByUser(username);
    }

    @Override
    public List<Task> findTasksByTag(String tagName) {
        return taskRepository.findTasksByTag(tagName);
    }

    private void validateTaskCreation(Task task) {
        if (task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Task due date cannot be in the past.");
        }
        if (task.getDueDate().isAfter(LocalDateTime.now().plusDays(3))) {
            throw new IllegalArgumentException("Task due date cannot be more than 3 days in advance.");
        }
        if (task.getTags() == null || task.getTags().size() < 2) {
            throw new IllegalArgumentException("At least two tags are required for each task.");
        }
    }
}
