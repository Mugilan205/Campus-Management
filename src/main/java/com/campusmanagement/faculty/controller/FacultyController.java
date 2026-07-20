package com.campusmanagement.faculty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @GetMapping
    public String faculty() {
        return "Welcome Faculty";
    }
}