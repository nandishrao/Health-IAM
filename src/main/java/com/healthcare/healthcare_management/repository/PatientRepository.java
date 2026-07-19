package com.healthcare.healthcare_management.repository;

import com.healthcare.healthcare_management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}