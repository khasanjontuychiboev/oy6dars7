package com.najot.oy5dars8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accept-document")
public class AcceptDocumentController {

    @GetMapping("/{id}")
    public String getDocument(@PathVariable Long id){
        return "Accepting Document id = "+id;
    }

    @GetMapping
    public String getDocuments(){
        return " Accepting Documents list";
    }

}
