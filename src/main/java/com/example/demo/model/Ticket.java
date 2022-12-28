package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Document(collection="booking_details")
	public class Ticket {
		
		public static final String SEQUENCE_NAME = "order_sequence";
		@Id
	    private int bookingOrderId;
		
	  
		
			@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Only digits are allowed for date in the format dd-mm-yyyy")
	    private String bookingDate;
	
	
	   @NotBlank(message="enter valid transactionMode")
	    private String transactionMode;
	   @NotBlank(message="enter valid quantity")
		private int quantity;
	   @NotBlank(message="enter valid totalCost")
		double totalCost;



	
}

