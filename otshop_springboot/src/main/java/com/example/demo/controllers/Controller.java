package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

class MessageDto {
    private int id;
    private String content;

    // Constructor
    public MessageDto(int id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @GetMapping
    public MessageDto hello() {
        return new MessageDto(0, "Bekend radi");
    }
}
