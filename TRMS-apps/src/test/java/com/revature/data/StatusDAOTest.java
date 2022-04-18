package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.revature.model.Status;

public class StatusDAOTest {

	private static StatusDAO StatusDAO = DAOFactory.getStatusDAO(); 
	private static Status testStat = new Status();
	int  id = testStat.getStatId();
	
	@Test
	public void getByIdExists() {
		Status status = StatusDAO.getById(id);
		assertEquals(testStat, status);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Status status = StatusDAO.getById(0);
		assertNull(status);
	}
}
