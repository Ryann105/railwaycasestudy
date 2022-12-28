package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DbSequenceTicketTest {


	@BeforeEach
	public void after() {
		DbSequenceTicket db = new DbSequenceTicket();
		
		db.setId("500");
		db.setSeq(500);
		
	}
	@Test
	public void Seq()
	{
		DbSequenceTicket db = new DbSequenceTicket();
		DbSequenceTicket db1 = new DbSequenceTicket();
		assertNotEquals(db, db1);
	}
	@Test
	public void testGetIdAndSequence()
	{
		DbSequenceTicket db = new DbSequenceTicket();
		db.setId("500");
		db.setSeq(500);
		assertEquals("500",db.getId());
		assertEquals(500, db.getSeq());
	}
}