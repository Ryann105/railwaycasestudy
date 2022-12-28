package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketServiceImplTest {

	
	@InjectMocks
	private TicketServiceImpl service;
	
	@Mock
	private TicketRepository repository;
	
	@Test
	void testServiceNotNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testRepositoryNotNull() {
		assertThat(repository).isNotNull();
	}
	
	@Test
	void testGetAllTickets() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		Ticket t2=new Ticket(2001,"22-09-2022","paytm",1,50);
		List<Ticket> TicketList=new ArrayList<Ticket>();
		TicketList.add(t1);
		TicketList.add(t2);
		when(repository.findAll()).thenReturn(TicketList);
		assertEquals(TicketList,service.getAllTickets());
		
	}
	
	@Test
	void testGetTicketById() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		when(repository.findById(2000)).thenReturn(Optional.of(t1));
		assertEquals(t1,service.getTicketById(2000));
	}
	
	@Test
	void testGetTicketByInvalidId() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		when(repository.findById(2000)).thenReturn(Optional.of(t1));
		try {
			assertThat(service.getTicketById(1)).as("Ticket with the id 1 doesn't exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddTickets() throws TicketNotFoundException, Exception {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		((List<Ticket>) assertThat(service.addTickets(t1)))
		.contains("added successfully....");
	
	}	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddTicketsAlreadyExists() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		when(repository.findById(2000)).thenReturn(Optional.of(t1));
	try {
		((List<Ticket>) assertThat(service.addTickets(t1)))
		.contains("Ticket with the id "+t1.getBookingOrderId()+" already exist");
	}catch(Exception e) {
		
	}
	}
	
	
	@Test
	void testDeleteTicketById() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		when(repository.findById(2000)).thenReturn(Optional.of(t1));
		assertThat(service.deleteTicket(2000))
		.contains("deleted successfully....");
	}
	
	@Test
	void testDeleteTicketByInvalidId() throws TicketNotFoundException {
		Ticket t1=new Ticket(2000,"29-09-2022","paytm",2,4790);
		when(repository.findById(2000)).thenReturn(Optional.of(t1));
		try {
			assertThat(service.deleteTicket(111))
			.contains("Ticket with the id "+t1.getBookingOrderId()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}
	
	
	}