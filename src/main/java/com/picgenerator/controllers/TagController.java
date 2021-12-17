package com.picgenerator.controllers;

import com.picgenerator.entities.Tag;
import com.picgenerator.services.TagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @Operation(summary = "Récupération d'un tag à partir de son identifiant")
    @RequestMapping(path = "/tag", method = RequestMethod.GET)
    public Tag getProjet(@RequestParam(value = "id") Integer id) {
        return tagService.getById(id);
    }

    @Operation(summary = "Création ou mise à jour d'un tag")
    @RequestMapping(path = "/tag", method = RequestMethod.PUT)
    public Tag addOrUpdateProjet(@Valid @RequestBody Tag tag) {
        return tagService.createOrUpdate(tag);
    }

    @Operation(summary = "Récupération de tous les tags")
    @RequestMapping(path = "/tags/all", method = RequestMethod.GET)
    public List<Tag> getTags() {
        return tagService.getAll();
    }

    @Operation(summary = "Suppression d'un tag à partir de son identifiant")
    @RequestMapping(path = "/tag", method = RequestMethod.DELETE)
    public void deleteProjet(@RequestParam(value = "id") Integer id) {
        tagService.deleteById(id);
    }

}
