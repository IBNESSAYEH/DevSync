package com.youcode.DevSyncV1.repository.implementation;

import com.youcode.DevSyncV1.entities.Tag;
import com.youcode.DevSyncV1.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    private EntityManager entityManager;

    public TagRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tag getTagById(long tagId) {
        return entityManager.find(Tag.class, tagId);
    }

    @Override
    public List<Tag> findAll() {
        return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    @Override
    public Tag save(Tag tag) {
        entityManager.getTransaction().begin();
        if (tag.getId() == 0) {
            entityManager.persist(tag); // New tag
        } else {
            tag = entityManager.merge(tag); // Update existing tag
        }
        entityManager.getTransaction().commit();
        return tag;
    }

    @Override
    public void delete(Tag tag) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(tag)) {
            tag = entityManager.merge(tag);
        }
        entityManager.remove(tag);
        entityManager.getTransaction().commit();
    }
}
