package com.healthcare.healthcare_management.config;

import com.healthcare.healthcare_management.entity.Patient;
import com.healthcare.healthcare_management.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public DataSeeder(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) {

        if (patientRepository.count() == 0) {

            Patient p1 = new Patient();
            p1.setName("John");
            p1.setHospital("Apollo");
            p1.setDepartment("Cardiology");

            Patient p2 = new Patient();
            p2.setName("Alice");
            p2.setHospital("Manipal");
            p2.setDepartment("Neurology");

            patientRepository.save(p1);
            patientRepository.save(p2);

            System.out.println("Patients seeded successfully.");
        }
    }
}