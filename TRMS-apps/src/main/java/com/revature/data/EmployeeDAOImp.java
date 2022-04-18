package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;

import services.ConnectionFactory;

public class EmployeeDAOImp implements EmployeeDAO{
	private static Connection  connection = ConnectionFactory.getConnection();
	
	public int create(Employee newObj) throws SQLException {
		
		String sql = "insert into emp(employee_id, manager_id, dept_id, fName, lName)" +
				"values(?, ?, ?, ?, ?)";
		try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
	            preparedStatement.setInt(1, newObj.getEmployeeId());
	            preparedStatement.setInt(2, newObj.getManagerId());
	            preparedStatement.setInt(3, newObj.getDeptId());
	            preparedStatement.setString(4, newObj.getfName());
	            preparedStatement.setString(5, newObj.getlName());
	            
	            connection.setAutoCommit(false);
	            
	            int count = preparedStatement.executeUpdate();
	            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (count > 0) {
	            	 System.out.println("Correct Employee!");
	            	 resultSet.next();
	                // int employeeId = resultSet.getInt(1);
	                 //newObj.setEmployeeId(employeeId);
	                 connection.commit();
	            }
	            else {
	                 System.out.println("Employee not created!");
	                 connection.rollback();
	            	}
		} catch (SQLException e){
           // print out what went wrong:
           e.printStackTrace();
           try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
       } finally {
       	try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
       }
		return  newObj.getEmployeeId();
	}

	public Employee getById(int emp_id) {
		Employee employee = null;
		
		
		 String sql = "SELECT * FROM employee WHERE employee_id = ?;";
		 try { 
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, emp_id);
           ResultSet resultSet = preparedStatement.executeQuery();
           // if result set doesn't point to a next value, that means something went wrong
           if(resultSet.next()) {
               employee = parseResultSet(resultSet);
               // now, we've created a pet Java object based on the info from our table
           } else {
               System.out.println("Something went wrong when querying Department!");
               // return null in this case
           }
       } catch (SQLException e){
           e.printStackTrace();
       }

  
	return employee;
	}

	private Employee parseResultSet(ResultSet resultSet) throws SQLException {
		Employee employee = new Employee();
        // do something with the return value:
		employee.setEmployeeId(resultSet.getInt(1));
		employee.setfName(resultSet.getString(2));
		employee.setlName(resultSet.getString(3));
		employee.setManagerId(resultSet.getInt(4));
        int emp_id = resultSet.getInt(5);
        String status = (emp_id == 1) ? "Available" : "Unavailable";
        employee.setfName(status);
		return employee;
	}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		
		String sql = "SELECT * FROM employee";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
            ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
             Employee employee = parseResultSet(resultSet);
            employees.add(employee);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employees;
	}

	public void update(Employee updatedObj) throws SQLException {
		String sql = "update employee set where empl_id = ?,  manager_id = ? fName = ?, lName = ?;";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setInt(1, updatedObj.getEmployeeId());
        	preparedStatement.setInt(2, updatedObj.getManagerId());
        	preparedStatement.setString(3,updatedObj.getfName());
        	preparedStatement.setString(4,updatedObj.getlName());
        	
        	connection.setAutoCommit(false);
        	
        	int count = preparedStatement.executeUpdate();
        	if(count != 1) {
        		connection.rollback();
        		throw new SQLException("ERROR: no object found to update");
        	} else connection.commit();
        	
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    		try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		throw e;
    	} finally {
    		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
	}

	public void delete(Employee objToDelete) throws SQLException{
		String sql = "delete from employee where emp_id = ?;";
		
		try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getDeptId());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: you were not able to delete the employee");
    		} else connection.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		throw e;
    	} finally {
    		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
		
	}

	public List<Employee> getByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getByLastName(String lasName) {
		// TODO Auto-generated method stub
		return null;
	}

}
