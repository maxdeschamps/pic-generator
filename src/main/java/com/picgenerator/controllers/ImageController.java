package com.picgenerator.controllers;

import com.picgenerator.services.ImageService;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ImageService imageService;

    @GetMapping("/generate")
    public ResponseEntity<Resource> Index(
            @RequestParam(value = "w", required = false) Integer width,
            @RequestParam(value = "h", required = false) Integer height,
            @RequestParam(value = "opt", required = false) Integer option,
            @RequestParam(value = "r", required = false) Integer red,
            @RequestParam(value = "g", required = false) Integer green,
            @RequestParam(value = "b", required = false) Integer blue,
            @RequestParam(value = "t", required = false) Integer tag,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "t-size", required = false) Integer textSize,
            @RequestParam(value = "t-color", required = false) String textColor
    ) throws IOException, InterruptedException, IM4JavaException {

        // Choice of the image
        String imageName = imageService.getImageWithParams(tag);

        // Get Buffered Image
        BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/assets/img/"+imageName));

        // Define operations
        IMOperation op = new IMOperation();
        op.addImage();
        // Resize or crop image
        op = imageService.opResizeOrCrop(op, width, height, option);
        // Colorize image
        op = imageService.opColorize(op, red, green, blue);
        // Add text
        op = imageService.opAddText(op, text, textSize, textColor);
        // Define image on jpg
        op.addImage("jpg:-");

        // Proecessing image
        image = imageService.imageProcessing(image, op);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        InputStream systemResourceAsStream = new ByteArrayInputStream(os.toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(new InputStreamResource(systemResourceAsStream));
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