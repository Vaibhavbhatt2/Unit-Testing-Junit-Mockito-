package com.example.TestingTutorial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TestingTutorial.entity.PatientRecord;
import com.example.TestingTutorial.repository.PatientRecordRepository;

@RestController
@RequestMapping("/patient")
public class PatientRecordController {
	
    @Autowired 
    PatientRecordRepository patientRecordRepository;
    
    @GetMapping("/")
    public List<PatientRecord> getAllPatients(){
    	
    	return this.patientRecordRepository.findAll();
    	
    }
    
    
    @GetMapping("/{name}")
    public PatientRecord getPatientByName(@PathVariable String name) {
    	return this.patientRecordRepository.getPatientByName(name);
    	
    }
    
    @PostMapping("/")
    public PatientRecord addPatientRecord(@RequestBody PatientRecord patient) {
    	
    	return this.patientRecordRepository.save(patient);
    	
    	
    }
    
    
    @PutMapping("/")
    public PatientRecord updatePatientRecord(@RequestBody PatientRecord patientRecord){
        Optional<PatientRecord> optionalRecord = patientRecordRepository.findById(patientRecord.getPatientId());
        PatientRecord existingPatientRecord = optionalRecord.get();

        existingPatientRecord.setName(patientRecord.getName());
        existingPatientRecord.setAge(patientRecord.getAge());
        existingPatientRecord.setAddress(patientRecord.getAddress());
    	
        return patientRecordRepository.save(existingPatientRecord);
    }

    @DeleteMapping("/{id}")
    public void deletePatientRecord(@PathVariable Long id) {
    	this.patientRecordRepository.deleteById(id);
    }

}
