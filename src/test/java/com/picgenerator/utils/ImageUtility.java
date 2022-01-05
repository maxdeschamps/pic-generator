package com.picgenerator.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageUtility {

    String baseUrl = "http://localhost:8080/";

    public BufferedImage getImageFromResponse(String endpoint) throws IOException {
         return ImageIO.read(new URL(baseUrl + endpoint));
    }
}
