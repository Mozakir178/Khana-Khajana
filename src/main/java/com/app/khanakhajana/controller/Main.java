package com.app.khanakhajana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping("/get")
    public String code(){
        return "index" ;
    }
}
