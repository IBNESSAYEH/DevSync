package com.youcode.DevSyncV1.repository;

import com.youcode.DevSyncV1.entities.Task;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public interface TaskRepository {


     Task getTaskById(long taskId);
     List<Task> findAll();
     Task save(Task task);
     void delete(Task task);
     List<Task> findTasksByUser(String username);
     List<Task> findTasksByTag(String tagName);
}
