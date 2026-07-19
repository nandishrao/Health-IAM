package com.healthcare.healthcare_management.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin Dashboard";
    }
}