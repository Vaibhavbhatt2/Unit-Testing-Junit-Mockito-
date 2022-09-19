package com.example.TestingTutorial;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.TestingTutorial.controller.PatientRecordController;
import com.example.TestingTutorial.entity.PatientRecord;
import com.example.TestingTutorial.repository.PatientRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

@WebMvcTest(PatientRecordController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class PatientRecordControllerTest {
	
	 @Autowired
	 MockMvc mockMvc;
	 
	 
	 @Autowired
	 ObjectMapper mapper;
	 
	    @MockBean
	    PatientRecordRepository patientRecordRepository;
	    
	    PatientRecord RECORD_1 = new PatientRecord(1l, "Rayven Yor", 23, "Cebu Philippines");
	    PatientRecord RECORD_2 = new PatientRecord(2l, "Vaibhav", 22, "India");
	    
		

	    
	    
	    
	    
	    @Test
	    @Order(1)
	    public void createRecord_success() throws Exception {
	        PatientRecord record = PatientRecord.builder()
	                .name("John Doe")
	                .age(47)
	                .address("New York USA")
	                .build();

	        Mockito.when(patientRecordRepository.save(record)).thenReturn(record);

	        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(record));

	           mockMvc.perform(mockRequest)
	                .andExpect(status().isOk());
	        
	        
		     System.out.println("New Patient record created successfully");


	        
	        
	        }
		
		
	    @Test
	    @Order(2)

	    public void getAllRecords_success() throws Exception {
	        List<PatientRecord> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2));
	     
	        
	        Mockito.when(patientRecordRepository.findAll()).thenReturn(records);
	        
	     ResultActions result=   mockMvc.perform(MockMvcRequestBuilders
	                .get("/patient/")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	     
	     String content = result.andReturn().getResponse().getContentAsString();
	     
	     System.out.println("All records fetched successfully: "+ content);
	        
	    }

	    
	    
	    @Test
	    @Order(3)

	    public void getPatientById_success() throws Exception {
	        Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(java.util.Optional.of(RECORD_1));

	          mockMvc.perform(MockMvcRequestBuilders
	                .get("/patient/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	        
		     
		     System.out.println("Record with Id "+ RECORD_1.getPatientId()+" fetched successfully.");


	        
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    @Test
	    @Order(4)

	    public void updatePatientRecord_success() throws Exception {
	        PatientRecord updatedRecord = PatientRecord.builder()
	                .patientId(1l)
	                .name("Rayven Zambo")
	                .age(23)
	                .address("Cebu Philippines")
	                .build();

	        Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(Optional.of(RECORD_1));
	        Mockito.when(patientRecordRepository.save(updatedRecord)).thenReturn(updatedRecord);

	        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(updatedRecord));

	        mockMvc.perform(mockRequest)
	                .andExpect(status().isOk());
	        
	        
		     System.out.println("Record with Id "+RECORD_1.getPatientId() +" updated successfully");


	        
	        
	        
	    }
	    
	    
	    
	    
	    
	    @Test
	    @Order(5)

	    public void deletePatientById_success() throws Exception {
	        Mockito.when(patientRecordRepository.findById(RECORD_2.getPatientId())).thenReturn(Optional.of(RECORD_2));
	        mockMvc.perform(MockMvcRequestBuilders.delete("/patient/2")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	        
	        
		     
		     System.out.println("Record with Id "+RECORD_2.getPatientId() +" deleted successfully");
	        
	        
	        
	    }
	    

}
