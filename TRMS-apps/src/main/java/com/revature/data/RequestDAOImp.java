package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Request;

import services.ConnectionFactory;

public class RequestDAOImp  implements RequestDAO{
	private static Connection connection = ConnectionFactory.getConnection();
	
	public int create(Request newObj) throws SQLException {
		
		
		String sql = "insert into Request(requst_id, submit_id, event_id, stat_id, cost, description, location, event_date" +
				"values(default, ?, ?, ?, ?, ?,? ,?)";
		try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
	            preparedStatement.setInt(1, newObj.getRequestId());
	            preparedStatement.setInt(2, newObj.getSubmitterId());
	            preparedStatement.setInt(3, newObj.getEventId());
	            preparedStatement.setInt(4, newObj.getStatusId());
	            preparedStatement.setDate(5, newObj.getEventDate());
	            preparedStatement.setDouble(6, newObj.getCost());
	            preparedStatement.setString(7, newObj.getDescription());
	            preparedStatement.setString(8, newObj.getLocation());
	            
	            int count = preparedStatement.executeUpdate();
	            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (count > 0) {
	            	 System.out.println("Correct Request!");
	                 // return the generated id:
	                 // before we call resultSet.next(), it's basically pointing to nothing useful
	                 // but moving that pointer allows us to get the information that we want
	                 resultSet.next();
	                 int requestId = resultSet.getInt(1);
	                 newObj.setRequestId(requestId);
	                 connection.commit(); // commit the changes to the DB
	             }
	             // if 0 rows are affected, something went wrong:
	             else {
	                 System.out.println("Request not created!");
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
	         
	         return newObj.getRequestId();
	}
	
	public Request getById(int request_id) {
		Request request = null;
		
		
		 String sql = "SELECT * FROM request WHERE request_id = ?";
		 try { 
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, request_id);
           ResultSet resultSet = preparedStatement.executeQuery();
           // if result set doesn't point to a next value, that means something went wrong
           if(resultSet.next()) {
               request = parseResultSet(resultSet);
               // now, we've created a pet Java object based on the info from our table
           } else {
               System.out.println("Something went wrong when querying Department!");
               // return null in this case
           }
       } catch (SQLException e){
           e.printStackTrace();
       }

  
	return request;
	}
	
	private Request parseResultSet(ResultSet resultSet) throws SQLException {
		Request request = new Request();
        // do something with the return value:
		request.setRequestId(resultSet.getInt(1));
		request.setSubmitterId(resultSet.getInt(2));
		request.setEventId(resultSet.getInt(3));
		request.setStatusId(resultSet.getInt(4));
        request.setDescription(resultSet.getString(5));
        request.setLocation(resultSet.getString(6));
        request.setEventDate(resultSet.getDate(7));
        request.setCost(resultSet.getInt(8));
        int request_id = resultSet.getInt(9);
        String status = (request_id == 1) ? "Available" : "Unavailable";
        request.setDescription(status);
		return request;
	}

	public List<Request> getAll() {
List<Request> requests = new ArrayList<Request>();
		
		String sql = "SELECT * FROM request";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
            ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
             Request request = parseResultSet(resultSet);
            requests.add(request);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return requests;
	}
	
	public void update(Request updatedObj) throws SQLException {
		String sql = "update employee set where request_id = ?,  submitter_id = ?, status_id =?, description = ?, location = ?, event-date = ?, cost = ?;";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setInt(1, updatedObj.getRequestId());
        	preparedStatement.setInt(2, updatedObj.getSubmitterId());
        	preparedStatement.setInt(3, updatedObj.getStatusId());
        	preparedStatement.setString(4,updatedObj.getDescription());
        	preparedStatement.setString(5,updatedObj.getLocation());
        	preparedStatement.setDate(6, updatedObj.getEventDate());
        	preparedStatement.setDouble(7, updatedObj.getCost());
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
	public void delete(Request objToDelete) throws SQLException {
		String sql = "delete from employee where request_id = ?;";
		
		try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getRequestId());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: you were not able to delete the request");
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
