package com.picgenerator.controllers;

import com.picgenerator.entities.User;
import com.picgenerator.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Récupération d'un user à partir de son identifiant")
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getProjet(@RequestParam(value = "id") Integer id) {
        return userService.getById(id);
    }

    @Operation(summary = "Création ou mise à jour d'un user")
    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public User addOrUpdateProjet(@Valid @RequestBody User user) {
        return userService.createOrUpdate(user);
    }

    @Operation(summary = "Récupération de tous les users")
    @RequestMapping(path = "/users/all", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getAll();
    }

    @Operation(summary = "Suppression d'un user à partir de son identifiant")
    @RequestMapping(path = "/user", method = RequestMethod.DELETE)
    public void deleteProjet(@RequestParam(value = "id") Integer id) {
        userService.deleteById(id);
    }

}
