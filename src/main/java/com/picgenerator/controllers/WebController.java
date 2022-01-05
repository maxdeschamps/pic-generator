package com.picgenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/sample")
    public String Index(Model model) {
        //model.addAttribute("imgSrc", myService.getImageSource(image));
        return "sample";
    }

}
