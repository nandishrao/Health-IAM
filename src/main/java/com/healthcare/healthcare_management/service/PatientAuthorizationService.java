package com.healthcare.healthcare_management.service;

import com.healthcare.healthcare_management.entity.Patient;
import com.healthcare.healthcare_management.repository.PatientRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class PatientAuthorizationService {

    private final PatientRepository repository;

    public PatientAuthorizationService(PatientRepository repository) {
        this.repository = repository;
    }

    public boolean canViewPatient(Long patientId,
                                  Authentication authentication) {

        JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;

        Patient patient = repository.findById(patientId)
                .orElseThrow();

        // SUPER ADMIN
        if (jwt.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_SUPER_ADMIN"::equals)) {

            return true;
        }

        // DOCTOR
        if (jwt.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_DOCTOR"::equals)) {

            String doctorHospital =
                    jwt.getToken().getClaimAsString("hospital");

            return doctorHospital.equals(patient.getHospital());
        }

        if (jwt.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_PATIENT"::equals)) {

            String username =
                    jwt.getToken().getClaimAsString("preferred_username");

            return username.equals(patient.getUsername());
        }

        return false;
    }
}