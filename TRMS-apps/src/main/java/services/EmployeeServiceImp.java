package services;

import java.sql.SQLException;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;
import com.revature.data.RequestDAO;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.model.Status;

public class EmployeeServiceImp implements EmployeeService{
	private EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	private RequestDAO requestDAO = DAOFactory.getRequestDAO();
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	public RequestDAO getRequestDAO() {
		return requestDAO;
	}
	public void setRequestDAO(RequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
	
	
	public Employee logIn (String fName, String lName ) throws IncorrectCredentialsException {
		Employee employee = employeeDAO.getById(0);
		if ( employee != null && employee.getfName().equals("")) {
			return employee;
		}else {
			throw new IncorrectCredentialsException();
		}
		
	}
	public Employee newEmp(Employee newEmp) throws UsernameAlreadyExistsException{
		int emp = 0;
		try {
			emp = employeeDAO.create(newEmp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (emp != 0) {
			newEmp.setEmployeeId(emp);
			return newEmp;
		}
		return null;
	}
	public Status status() {
		// TODO Auto-generated method stub
		return null;
	}
	public Request request() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
