package com.youcode.DevSyncV1.repository;

import com.youcode.DevSyncV1.entities.Tag;

import java.util.List;

public interface TagRepository {



    Tag getTagById(long tagId);
    List<Tag> findAll();
    Tag save(Tag tag);
    void delete(Tag tag);
}
