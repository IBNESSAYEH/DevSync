package com.youcode.DevSyncV1.service.implementation;

import com.youcode.DevSyncV1.entities.Tag;
import com.youcode.DevSyncV1.repository.TagRepository;
import com.youcode.DevSyncV1.repository.implementation.TagRepositoryImpl;
import com.youcode.DevSyncV1.service.TagService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(EntityManager entityManager) {
        this.tagRepository = new TagRepositoryImpl(entityManager);
    }

    @Override
    public Tag getTagById(long tagId) {
        return tagRepository.getTagById(tagId);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag, long tagId) {
        Tag existingTag = tagRepository.getTagById(tagId);
        if (existingTag != null) {
            existingTag.setName(tag.getName());
            return tagRepository.save(existingTag);
        }
        return null;
    }

    @Override
    public void deleteTag(long tagId) {
        Tag existingTag = tagRepository.getTagById(tagId);
        if (existingTag != null) {
            tagRepository.delete(existingTag);
        }
    }
}
