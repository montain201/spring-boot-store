package com.example.store.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /*@Value("${spring.application.name}")
    private String appName;*/

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Gadir");
        //System.out.println("appName:"+ appName);

        return "index";
    }

}
