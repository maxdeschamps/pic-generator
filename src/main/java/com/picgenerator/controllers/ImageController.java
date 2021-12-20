package com.picgenerator.controllers;

import com.picgenerator.services.ImageService;
import org.im4java.core.IM4JavaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import com.picgenerator.entities.Image;
import com.picgenerator.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ImageController {
    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ImageService imageService;

    @GetMapping("/test")
    public ResponseEntity<Resource> Index() throws IOException, InterruptedException, IM4JavaException {
        BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/assets/img/noel_1.jpg"));
        image = imageService.imageProcessing(image);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        InputStream systemResourceAsStream = new ByteArrayInputStream(os.toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(new InputStreamResource(systemResourceAsStream));
        //URL resourceUrl = this.getClass().getClassLoader().getResource("/assets/img/noel_1.jpg")
    }

    @Operation(summary = "Récupération d'une image à partir de son identifiant")
    @RequestMapping(path = "/image", method = RequestMethod.GET)
    public Image getImage(@RequestParam(value = "id") Integer id) {
        return imageService.getById(id);
    }

    @Operation(summary = "Création ou mise à jour d'une image")
    @RequestMapping(path = "/image", method = RequestMethod.PUT)
    public Image addOrUpdateImage(@Valid @RequestBody Image image) {
        return imageService.createOrUpdate(image);
    }

    @Operation(summary = "Récupération de toutes les images")
    @RequestMapping(path = "/images/all", method = RequestMethod.GET)
    public List<Image> getAllImage() {
        return imageService.getAll();
    }

    @Operation(summary = "Suppression d'une image à partir de son identifiant")
    @RequestMapping(path = "/image", method = RequestMethod.DELETE)
    public void deleteImage(@RequestParam(value = "id") Integer id) {
        imageService.deleteById(id);
    }
}