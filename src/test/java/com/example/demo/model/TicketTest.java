package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TicketTest {
	

		Ticket t1;
		@BeforeEach
		public void before() {
			 t1 = new Ticket(4500,"22-09-2022","paytm", 2, 4790);
		}
		
		
		@AfterEach
		public void after() {
			t1=null;
		}
		
		@Test
		void testGetBookingOrderId() {
		assertEquals(2000, t1.getBookingOrderId());
		}

		@Test
		void testGetbookingDate() {
			assertEquals("29-09-2022", t1.getBookingDate());
		}

		@Test
		void testGetTransactionMode() {
			assertEquals("paytm", t1.getTransactionMode());
		}
		
		@Test
         void testGetQuantity() {
			assertEquals(2,t1.getQuantity());
		}
		
		@Test
		void testGettotalCost() {
			assertEquals(4790, t1.getTotalCost());
		}


		@Test
		void testSetBookingOrderId() {
			t1.setBookingOrderId(2000);
			assertEquals(2000, t1.getBookingOrderId());
		}

		@Test
		void testSetbookingDate() {
			t1.setBookingDate("29-09-2022");
			assertEquals("29-09-2022", t1.getBookingDate());
		}

		@Test
		void testSettransactionMode() {
			t1.setTransactionMode("paytm");
			assertEquals("paytm",t1.getTransactionMode());
		}

		@Test
		void testSetQuantity() {
			t1.setQuantity(2);
			assertEquals(2,t1.getQuantity());
		}
		@Test
		void testSetTotalCost() {
			t1.setTotalCost(4790);
			assertEquals(4790, t1.getTotalCost());
		}
		
}