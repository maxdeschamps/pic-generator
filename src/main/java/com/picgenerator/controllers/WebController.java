package com.picgenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/sample")
    public String Index(Model model) {
        //model.addAttribute("imgSrc", myService.getImageSource(image));
        return "sample";
    }

}
