package com.nazar.cookbooknazar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/")
    public String home() {
        return "Main";
        // информация о проекте находится на странице /about
    }

}