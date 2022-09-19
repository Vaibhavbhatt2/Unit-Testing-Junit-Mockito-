package com.example.TestingTutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Builder;

@Entity
@Table(name="patient_record")
@Builder
public class PatientRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long patientId;

	@NonNull
	String name;

	@NonNull
	Integer age;

	@NonNull
	String address;

	public PatientRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientRecord(Long patientId, String name, Integer age, String address) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PatientRecord [patientId=" + patientId + ", name=" + name + ", age=" + age + ", address=" + address
				+ "]";
	}
	
	
	

}
