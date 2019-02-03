package com.app.service;


import com.app.model.ImageModel;
import com.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Writer {
    private final ImageRepository imageRepository;

    @Autowired
    public Writer(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void saveImage(ImageModel imageModel) {
        imageRepository.save(imageModel);
    }
}
