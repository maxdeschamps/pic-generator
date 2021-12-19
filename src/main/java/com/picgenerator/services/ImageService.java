package com.picgenerator.services;

import com.picgenerator.repositories.ImageRepository;
import org.im4java.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public BufferedImage imageProcessing(BufferedImage image) throws IOException, InterruptedException, IM4JavaException {

        String imPath="C:\\Program Files\\ImageMagick";

        IMOperation op = new IMOperation();
        op.addImage();
        op.colorize(30, 40, 0);
        op.addImage("jpg:-");

        // set up command
        ConvertCmd convert = new ConvertCmd();
        convert.setSearchPath(imPath);
        Stream2BufferedImage s2b = new Stream2BufferedImage();
        convert.setOutputConsumer(s2b);

        // run command and extract BufferedImage from OutputConsumer
        convert.run(op,image);
        BufferedImage convertImage = s2b.getImage();

        return convertImage;
    }
}
