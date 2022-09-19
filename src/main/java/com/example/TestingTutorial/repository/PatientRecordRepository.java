package com.example.TestingTutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TestingTutorial.entity.PatientRecord;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long>{
	
	public PatientRecord getPatientByName(String name);
	

}
