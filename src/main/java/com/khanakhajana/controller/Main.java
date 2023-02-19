package com.khanakhajana.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @GetMapping("/get")
    public String method(){
        return "Test" ;
    }
}
