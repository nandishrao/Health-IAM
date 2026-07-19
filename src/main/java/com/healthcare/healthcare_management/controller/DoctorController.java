package com.healthcare.healthcare_management.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @GetMapping
    public String doctors() {
        return "Doctor List";
    }
}