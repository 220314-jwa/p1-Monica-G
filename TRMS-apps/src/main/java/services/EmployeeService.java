package services;

import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.model.Status;

public interface EmployeeService {

	
	
	public Employee logIn(String fName, String lName)throws IncorrectCredentialsException;


	public Employee newEmp(Employee newEmp) throws UsernameAlreadyExistsException;
	
	public Status status();
	
	public Request request();


	public Object getUsername();
	

}

