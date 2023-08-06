package com.project.searchone.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")

public class TmpController {

    @GetMapping()
    public String main(){
        return "hi";
    }
    
}
