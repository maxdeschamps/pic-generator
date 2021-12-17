package com.picgenerator.services;

import com.picgenerator.entities.Image;
import com.picgenerator.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getById(Integer id) {
        return imageRepository.findById(id).orElse(null);
    }

    public Image createOrUpdate(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public void deleteById(Integer id) {
        imageRepository.delete(getById(id));
    }

}
