package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.revature.model.Request;

public class RequestDAOTest {

	private static RequestDAO RequestDAO = DAOFactory.getRequestDAO(); 
	private static Request testReq = new Request();
	int  id = testReq.getRequestId();
	
	
	@Test
	public void getByIdExists() {
		Request request = RequestDAO.getById(id);
		assertEquals(testReq, request);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Request request = RequestDAO.getById(0);
		assertNull(request);
	}
	
	
}
