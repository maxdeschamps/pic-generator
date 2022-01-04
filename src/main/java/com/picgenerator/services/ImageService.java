package com.picgenerator.services;

import com.picgenerator.entities.Image;
import com.picgenerator.repositories.ImageRepository;
import com.picgenerator.repositories.TagRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Value("${imageMagick.path}")
    private String imageMagickPath;

    public String getImageWithParams(Integer tag) {
        List<Image> images = null;

        if (tag != null) {
            images = imageRepository.findByTag(tag);
        } else {
            images = imageRepository.findAll();
        }

        if (images != null) {
            Random rand = new Random();
            Image image = images.get(rand.nextInt(images.size()));
            return image.getSrc();
        } else {
            // default img
            return "ville_2.jpg";
        }
    }

    public IMOperation opResizeOrCrop(IMOperation op, Integer width, Integer height, Integer option) {
        if (width != null && height != null) {
            if (option != null && option == 2) {
                // Resize image
                op.resize(width, height);
            } else {
                // Crop image
                op.resize(width, height, "^");
                op.gravity("center");
                op.crop(width, height, 0, 0);
            }
        }
        return op;
    }

    public IMOperation opColorize(IMOperation op, Integer red, Integer green, Integer blue) {
        if (red != null || green != null || blue != null) {
            if (red == null) {
                red = 0;
            }

            if (green == null) {
                green = 0;
            }

            if (blue == null) {
                blue = 0;
            }
            op.colorize(red, green, blue);
        }
        return op;
    }

    public IMOperation opAddText(IMOperation op, String text, Integer textSize, String textColor) {
        if (text != null && text != "") {
            if (textSize != null) {
                op.pointsize(textSize);
            } else {
                op.pointsize(20);
            }
            if (textColor != null) {
                op.fill("#"+textColor);
            } else {
                op.fill("black");
            }
            op.annotate(0, 0, 0, 0, text);
        }
        return op;
    }

    public BufferedImage imageProcessing(BufferedImage image, IMOperation op) throws IOException, InterruptedException, IM4JavaException {

        // Set up command
        ConvertCmd convert = new ConvertCmd();
        convert.setSearchPath(imageMagickPath);
        Stream2BufferedImage s2b = new Stream2BufferedImage();
        convert.setOutputConsumer(s2b);

        // Run command and extract BufferedImage from OutputConsumer
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