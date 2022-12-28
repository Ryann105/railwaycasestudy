package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.TicketNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

@Autowired
private TicketRepository ticketRepository;
Logger logger = LoggerFactory.getLogger(TicketService.class);

@Override
public List<Ticket> getAllTickets() throws TicketNotFoundException {
	logger.info("get all orders from here");
	return ticketRepository.findAll();
}



@Override
public Ticket getTicketById(int id) throws TicketNotFoundException {
	Ticket tickets=ticketRepository.findById(id).orElseThrow(()-> new  TicketNotFoundException("order Not Found"+id));

	return tickets;
}


@Override
public Ticket addTickets(Ticket ticket) throws NoProperDataException {
	logger.info("start");
	if(ticket!=null) 
	{
		ticketRepository.save(ticket);
		logger.debug("tickets added");
	}
	else
	{
		throw new NoProperDataException("Please fill fields");
	}
	return ticket;
}





@Override
public String deleteTicket(int id) throws TicketNotFoundException {
	logger.info("Start delete");
	Optional isRemoved =ticketRepository.findById(id);
	if(isRemoved.isPresent())
	{
		ticketRepository.deleteById(id);
		logger.debug("deleted successfully {}",isRemoved.get());
	}
	else
	{
		throw new TicketNotFoundException("Id Not Available");
	}
	logger.info(" deleted End");
	return " deleted successfully";
}


}

