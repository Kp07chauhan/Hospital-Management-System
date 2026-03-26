package com.management.hospital;

import com.management.hospital.entity.Patient;
import com.management.hospital.repository.PatientRepository;
import com.management.hospital.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethod(){
        Patient patient= patientService.getPatientById(1L);
        System.out.println(patient);

    }

}

























