package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.revature.model.Department;

public class DepartmentDaoTest {
	
	private static DepartmentDAO DepartmentDAO = DAOFactory.getDepartmentDAO(); 
	private static Department testDep = new Department();
	int  id = testDep.getDeptId();
	
	@Test
	public void getByIdExists() {
		Department department = DepartmentDAO.getById(id);
		assertEquals(testDep, department);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Department department = DepartmentDAO.getById(0);
		assertNull(department);
	}
}
