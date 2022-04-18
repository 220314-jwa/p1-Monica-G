package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Status;

import services.ConnectionFactory;

public class StatusDAOImp implements StatusDAO{
	
	private static Connection connection = ConnectionFactory.getConnection();
	
	public int create(Status newObj) throws SQLException {
		
		String sql = "insert into stat(stat_id, stat_Name)" +
		"values(default, ?)";
		try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
	            preparedStatement.setInt(1, newObj.getStatId());
	            preparedStatement.setString(2, newObj.getStatName());
	            
	            int count = preparedStatement.executeUpdate();
	            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (count > 0) {
	            	 System.out.println("Correct Status!");
	            	 resultSet.next();
	                 int deptId = resultSet.getInt(1);
	                 newObj.setStatId(deptId);
	                 connection.commit();
	            }
	            else {
	                 System.out.println("Status not created!");
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
		return newObj.getStatId();
	}

	public Status getById(int stat_id) {
		Status status= null;
		
		
		 String sql = "SELECT * FROM status WHERE stat_id = ?;";
		 try { 
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1, stat_id);
           ResultSet resultSet = preparedStatement.executeQuery();
           // if result set doesn't point to a next value, that means something went wrong
           if(resultSet.next()) {
               status = parseResultSet(resultSet);
               // now, we've created a pet Java object based on the info from our table
           } else {
               System.out.println("Something went wrong when querying Department!");
               // return null in this case
           }
       } catch (SQLException e){
           e.printStackTrace();
       }

  
	return status;
	}

	private Status parseResultSet(ResultSet resultSet) throws SQLException {
		Status status = new Status();
        // do something with the return value:
		status.setStatId(resultSet.getInt(1));
        status.setStatName(resultSet.getString(2));
       
        int stat_id = resultSet.getInt(6);
        String state = (stat_id == 1) ? "Available" : "Unavailable";
        status.setStatName(state);
		return status;
	}

	public List<Status> getAll() {
		List<Status> statuss = new ArrayList<Status>();
		
		String sql = "SELECT * FROM status";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
            ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
             Status status = parseResultSet(resultSet);
            statuss.add(status);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return statuss;
	}

	public void update(Status updatedObj) throws SQLException {
		String sql = "update department set stat_name = ?,  where stat_id = ? ;";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setString(1,updatedObj.getStatName());
        	preparedStatement.setInt(2, updatedObj.getStatId());
        	
        	
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

	public void delete(Status objToDelete) throws SQLException {
String sql = "delete from department where stat_id = ?;";
		
		try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getStatId());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: you were not able to delete the status");
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
