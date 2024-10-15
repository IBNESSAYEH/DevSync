package com.youcode.DevSyncV1.repository.implementation;

import com.youcode.DevSyncV1.entities.Tag;
import com.youcode.DevSyncV1.repository.TagRepository;
import jakarta.persistence.*;

import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    private EntityManagerFactory emf;

    public TagRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("myJPAUnit");
    }

    @Override
    public Tag getTagById(long tagId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Tag.class, tagId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tag> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tag save(Tag tag) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (tag.getId() == 0) {
                entityManager.persist(tag); // New tag
            } else {
                tag = entityManager.merge(tag); // Update existing tag
            }
            transaction.commit();
            return tag;
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
    public void delete(Tag tag) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (!entityManager.contains(tag)) {
                tag = entityManager.merge(tag);
            }
            entityManager.remove(tag);
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
}