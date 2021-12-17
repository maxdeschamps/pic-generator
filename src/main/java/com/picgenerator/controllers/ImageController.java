package com.picgenerator.controllers;

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
    private ImageService imageService;

    @Operation(summary = "Récupération d'une image à partir de son identifiant")
    @RequestMapping(path = "/image", method = RequestMethod.GET)
    public Image getProjet(@RequestParam(value = "id") Integer id) {
        return imageService.getById(id);
    }

    @Operation(summary = "Création ou mise à jour d'une image")
    @RequestMapping(path = "/image", method = RequestMethod.PUT)
    public Image addOrUpdateProjet(@Valid @RequestBody Image image) {
        return imageService.createOrUpdate(image);
    }

    @Operation(summary = "Récupération de toutes les images")
    @RequestMapping(path = "/images/all", method = RequestMethod.GET)
    public List<Image> getTags() {
        return imageService.getAll();
    }

    @Operation(summary = "Suppression d'une image à partir de son identifiant")
    @RequestMapping(path = "/image", method = RequestMethod.DELETE)
    public void deleteProjet(@RequestParam(value = "id") Integer id) {
        imageService.deleteById(id);
    }

}
