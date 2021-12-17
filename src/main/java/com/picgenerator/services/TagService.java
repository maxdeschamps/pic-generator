package com.picgenerator.services;

import com.picgenerator.entities.Tag;
import com.picgenerator.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag getById(Integer id) {
        return tagRepository.findById(id).orElse(null);
    }

    public Tag createOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public void deleteById(Integer id) {
        tagRepository.delete(getById(id));
    }

}
