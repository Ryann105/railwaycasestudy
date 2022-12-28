package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;


public interface TicketService {
	public  List<Ticket> getAllTickets() throws  TicketNotFoundException;
	public Ticket getTicketById( int id) throws TicketNotFoundException;
	public Ticket addTickets( Ticket ticket)  throws NoProperDataException;
	public String deleteTicket( int id) throws TicketNotFoundException;
}

