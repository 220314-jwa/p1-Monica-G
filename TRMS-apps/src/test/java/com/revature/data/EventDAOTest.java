package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.revature.model.Event;

public class EventDAOTest {
	private static EventDAO EventDAO = DAOFactory.getEventDAO(); 
	private static Event testEvent = new Event();
	int  id = testEvent.getEventId();
	
	@Test
	public void getByIdExists() {
		Event event = EventDAO.getById(id);
		assertEquals(testEvent, event);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Event event = EventDAO.getById(0);
		assertNull(event);
	}
}
