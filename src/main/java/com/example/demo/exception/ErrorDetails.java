package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    
	private String ErrorMessage;
	private LocalDateTime TimeStamp;
	public void setErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	public void setTimeStamp(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
	
	
}
