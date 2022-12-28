package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.DbSequenceTicket;


@Service
public class SequenceGeneratorService {
	
	    @Autowired
	    private MongoOperations mongoOperations;

	
	 
	  
	  public int getSequenceNumberForTickets(String sequenceName) {
	        //get sequence no
	        Query query = new Query(Criteria.where("id").is(sequenceName));
	        //update the sequence no
	        Update update = new Update().inc("seq",300);
	        //modify in document
	        //customer id will start from 300
	        DbSequenceTicket counter = mongoOperations
	                .findAndModify(query,
	                        update, options().returnNew(true).upsert(true),
	                        DbSequenceTicket.class);

	        return !Objects.isNull(counter) ? counter.getSeq() :1;
	    }
	    

}


