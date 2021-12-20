package com.picgenerator.services;

import com.picgenerator.entities.Image;
import com.picgenerator.repositories.ImageRepository;
import org.im4java.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Value("${imageMagick.path}")
    private String imageMagickPath;

    public BufferedImage imageProcessing(BufferedImage image) throws IOException, InterruptedException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage();
        op.colorize(30, 40, 0);
        op.addImage("jpg:-");

        // set up command
        ConvertCmd convert = new ConvertCmd();
        convert.setSearchPath(imageMagickPath);
        Stream2BufferedImage s2b = new Stream2BufferedImage();
        convert.setOutputConsumer(s2b);

        // run command and extract BufferedImage from OutputConsumer
        convert.run(op,image);
        BufferedImage convertImage = s2b.getImage();

        return convertImage;
    }

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