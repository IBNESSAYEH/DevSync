package com.youcode.DevSyncV1.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    private boolean completed;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "created_by")
    private String createdBy;

    private int tokens;


    public Task() {
    }

    public Task(String title, String description, LocalDateTime dueDate, Set<Tag> tags, String createdBy) {
        if (dueDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La tâche ne peut pas être créée dans le passé.");
        }
        if (tags.size() < 2) {
            throw new IllegalArgumentException("Veuillez entrer au moins deux tags.");
        }
        if (dueDate.isAfter(LocalDateTime.now().plusDays(3))) {
            throw new IllegalArgumentException("La planification des tâches est limitée à 3 jours à l'avance.");
        }

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.tags = new HashSet<>(tags);
        this.createdBy = createdBy;
        this.assignedTo = createdBy;
        this.completed = false;
        this.tokens = 2;
    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeTask() {
        if (LocalDateTime.now().isAfter(dueDate)) {
            throw new IllegalArgumentException("Vous devez marquer la tâche comme terminée avant la date limite.");
        }
        this.completed = true;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void assignTo(String user) {
        if (!user.equals(this.assignedTo)) {
            throw new IllegalArgumentException("Un utilisateur peut attribuer des tâches uniquement à lui-même.");
        }
        this.assignedTo = user;
    }

    public int getTokens() {
        return tokens;
    }

    public void replaceTask(Task newTask) {
        if (this.assignedTo.equals(newTask.assignedTo) || tokens <= 0) {
            throw new IllegalArgumentException("Vous n'avez pas assez de jetons pour remplacer cette tâche.");
        }
        this.assignedTo = newTask.assignedTo;
        this.tokens--; // Deduct a token for replacement
    }

    public void deleteTask() {
        // Implementation for deletion logic
    }
}