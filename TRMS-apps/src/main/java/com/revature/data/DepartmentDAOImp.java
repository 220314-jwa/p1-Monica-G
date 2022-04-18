package com.revature.data;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Department;
import services.ConnectionFactory;


public class DepartmentDAOImp implements DepartmentDAO{
	
	private static Connection connection = ConnectionFactory.getConnection();

	public int create(Department newObj) throws SQLException {
		
		
		String sql = "insert into department(dept_id, dept_head_id, dept_name" +
	"values(?, ?, ?)";
		try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
	            preparedStatement.setInt(1, newObj.getDeptId());
	            preparedStatement.setString(2, newObj.getDeptName());
	            preparedStatement.setInt(2, newObj.getDeptHeadId());
	            
	            int count = preparedStatement.executeUpdate();
	            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (count > 0) {
	            	 System.out.println("Correct Department!");
	                 // return the generated id:
	                 // before we call resultSet.next(), it's basically pointing to nothing useful
	                 // but moving that pointer allows us to get the information that we want
	                 resultSet.next();
	                 int deptId = resultSet.getInt(1);
	                 newObj.setDeptId(deptId);
	                 connection.commit(); // commit the changes to the DB
	             }
	             // if 0 rows are affected, something went wrong:
	             else {
	                 System.out.println("Department not created!");
	                 connection.rollback(); // rollback the changes
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
	         
	         return newObj.getDeptId();
	}

	public Department getById(int dept_id) {
		Department department = null;
		
		
			 String sql = "SELECT * FROM department WHERE dept_id = ?;";
			 try { 
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, dept_id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            // if result set doesn't point to a next value, that means something went wrong
	            if(resultSet.next()) {
	                department = parseResultSet(resultSet);
	                // now, we've created a pet Java object based on the info from our table
	            } else {
	                System.out.println("Something went wrong when querying Department!");
	                // return null in this case
	            }
	        } catch (SQLException e){
	            e.printStackTrace();
	        }

	   
		return department;
	}

	private Department parseResultSet(ResultSet resultSet) throws SQLException {
		Department department = new Department();
        // do something with the return value:
		department.setDeptId(resultSet.getInt(1));
        department.setDeptName(resultSet.getString(2));
        department.setDeptHeadId(resultSet.getInt(3));
        int dep_id = resultSet.getInt(6);
        String status = (dep_id == 1) ? "Available" : "Unavailable";
        department.setDeptName(status);
		return department;
	}

	public List<Department> getAll() {
		List<Department> departments = new ArrayList<Department>();
		
		String sql = "SELECT * FROM department";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
            ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
             Department department = parseResultSet(resultSet);
            departments.add(department);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return departments;
	}

	public void update(Department updatedObj) throws SQLException {
		String sql = "update department set name = ?,  where dept_id = ?, dept_head_id = ? ;";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setString(1,updatedObj.getDeptName());
        	preparedStatement.setInt(2, updatedObj.getDeptId());
        	preparedStatement.setInt(3, updatedObj.getDeptHeadId());
        	
        	
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

	public void delete(Department objToDelete) throws SQLException {
		String sql = "delete from department where dept_id = ?;";
		
		try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getDeptId());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: you were not able to delete the department");
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

}
