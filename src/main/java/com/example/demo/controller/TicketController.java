package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketServiceImpl;
import com.example.demo.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequestMapping("api/v3")
public class TicketController {
	@Autowired

	private TicketServiceImpl ticketServiceimpl;
	Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/alltickets")
	public ResponseEntity<List<Ticket>> getAllTickets() throws TicketNotFoundException {
		{

			List<Ticket> tickets = ticketServiceimpl.getAllTickets();
			logger.info("starting  of get mapping");

			if (!tickets.isEmpty()) {
				logger.debug("tickets are {}" + tickets);
				return new ResponseEntity<>(tickets, HttpStatus.OK);
			} else {
				logger.debug(" no tickets found ");
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

		}
	}

	@GetMapping("tickets/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable int id) throws TicketNotFoundException {
		Ticket tickets = ticketServiceimpl.getTicketById(id);
		if (tickets != null) {
			return ResponseEntity.ok().body(tickets);
		} else {
			return new ResponseEntity(tickets, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/addTickets")
	public ResponseEntity<Ticket> addTickets(@RequestBody Ticket ticket) throws NoProperDataException {

		if (ticket != null) {

			ticket.setBookingOrderId(service.getSequenceNumberForTickets(Ticket.SEQUENCE_NAME));
			ticketServiceimpl.addTickets(ticket);
			logger.error("added oders");
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);

		} else {
			throw new NoProperDataException("Please fill fields");

		}

	}

	@DeleteMapping(path = "/tickets/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable int id) throws TicketNotFoundException {
		int count = 1;
		for (int i = 1; i >= count; count++) {
			if (count == 1) {
				try {
					ticketServiceimpl.deleteTicket(id);
				} catch (TicketNotFoundException e) {
					logger.error(e.getMessage());
				}
			} else {
				logger.info("id not found");
			}
		}
		return ResponseEntity.ok(" deleted operation done ");

	}

}
