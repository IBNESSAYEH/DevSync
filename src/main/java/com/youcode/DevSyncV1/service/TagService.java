package com.youcode.DevSyncV1.service;

import com.youcode.DevSyncV1.entities.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(long tagId);
    List<Tag> findAll();
    Tag createTag(Tag tag);
    Tag updateTag(Tag tag, long tagId);
    void deleteTag(long tagId);
}
