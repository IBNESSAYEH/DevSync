package com.youcode.DevSyncV1.service;

import com.youcode.DevSyncV1.entities.Task;

import java.util.List;

public interface TaskService {
    Task getTaskById(long taskId);
    List<Task> findAll();
    Task createTask(Task task);
    Task updateTask(Task task, long taskId);
    void deleteTask(long taskId);
    List<Task> findTasksByUser(String username);
    List<Task> findTasksByTag(String tagName);
}
