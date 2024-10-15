package com.youcode.DevSyncV1.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

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
    @Column(name = "replacement_order")
    private Boolean replacementOrder;
    @Column(name = "deleting_order")
    private Boolean deletingOrder;
    @Column(name = "replacement_possibility")
    private Boolean replacementPossibility;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private int tokens;


    public Task() {
    }

    public Task(String title, String description, LocalDateTime dueDate, User createdBy, int tokens) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.createdBy = createdBy;
        this.tokens = tokens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getReplacementOrder() {
        return replacementOrder;
    }

    public void setReplacementOrder(Boolean replacementOrder) {
        this.replacementOrder = replacementOrder;
    }

    public Boolean getDeletingOrder() {
        return deletingOrder;
    }

    public void setDeletingOrder(Boolean deletingOrder) {
        this.deletingOrder = deletingOrder;
    }

    public Boolean getReplacementPossibility() {
        return replacementPossibility;
    }

    public void setReplacementPossibility(Boolean replacementPossibility) {
        this.replacementPossibility = replacementPossibility;
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}