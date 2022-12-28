package com.example.demo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer> {

}

