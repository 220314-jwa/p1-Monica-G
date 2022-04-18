package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.revature.model.Employee;

public class EmployeeDaoTest {

	private static EmployeeDAO EmployeeDAO = DAOFactory.getEmployeeDAO(); 
	private static Employee testEmp = new Employee();
	int  id = testEmp.getEmployeeId();
	
	
	@Test
	public void getByIdExists() {
		//int id = testEmp.getEmployeeId();
		
		Employee employee = EmployeeDAO.getById(id);
		assertEquals(testEmp, employee);
	}
	
	@Test
	public void getValidEmpId() {
		String employeeString = "";
		Employee employeeDAO = EmployeeDAO.getById(id);
		assertEquals(employeeString, employeeDAO.getfName());
	}
	
	@Test 
	public void testUpdate() throws SQLException {
		//int  id = testEmp.getEmployeeId();
		Employee employee = EmployeeDAO.getById(id);
		employee.setfName("");
		EmployeeDAO.update(employee);
		Employee employee2 = EmployeeDAO.getById(id);
		System.out.println(employee2);
		assertEquals("", employee.getfName());	
	}
	
	@Test
	public void testGetIdNum() {
		Employee employeeOutput = EmployeeDAO.getById(id);
		assertNull(employeeOutput);
	}
	
	
	
}
