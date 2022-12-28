package com.example.demo.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.BookingDetailsApplication;
import com.example.demo.controller.TicketController;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = BookingDetailsApplication.class)

public class TicketControllerTest {
	@InjectMocks
	private TicketController ticketcontroller;

	@InjectMocks
	private TicketService ticketService;
	//@MockBean
	//private TicketRepository ticketrepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	void addTickets() throws TicketNotFoundException, Exception {
		Ticket t1 = new Ticket(2000,"29-09-2022","paytm",2,4790);

		mockMvc.perform(
				post("/addtickets").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(t1)))
				.andExpect(status().isOk()).andDo(print());
	}


	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void getAlltickets() throws Exception {
		List<Ticket> TicketList = new ArrayList<>();
		TicketList.add(new Ticket(2000,"29-09-2022","paytm",2,4790));

		when(ticketService.getAllTickets()).thenReturn(TicketList);

		String url = "/viewAll";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		String exceptedJsonResponse = objectMapper.writeValueAsString(TicketList);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

	}

	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void getTicketById() throws Exception {
		Ticket t1 = new Ticket();
		t1.setBookingOrderId(2000);
		t1.setBookingDate("29-09-2022");
		t1.setTransactionMode("paytm");
		t1.setQuantity(2);
		t1.setTotalCost(4790);
		
		when(ticketService.getTicketById(2000)).thenReturn(t1);
		String url = "/View/1";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		String exceptedJsonResponse = objectMapper.writeValueAsString(t1);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

	}




	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void deleteTicket() throws TicketNotFoundException, Exception {
		Ticket TicketList = new Ticket(2000,"29-09-2022","paytm",2,4790);

		when(ticketService.getTicketById(1)).thenReturn(TicketList);

		mockMvc.perform(delete("/delete/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());

	}

}
