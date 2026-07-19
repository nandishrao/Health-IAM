package com.healthcare.healthcare_management.controller;

import com.healthcare.healthcare_management.entity.Patient;
import com.healthcare.healthcare_management.repository.PatientRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository repository;
    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/{id}")
    @PreAuthorize("@patientAuthorizationService.canViewPatient(#id, authentication)")
    public Patient getPatient(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow();
    }
}