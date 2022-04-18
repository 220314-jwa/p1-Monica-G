package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Event;

import services.ConnectionFactory;

public class EventDAOImp implements EventDAO {
	private static Connection connection = ConnectionFactory.getConnection();
	
	public int create(Event newObj) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		
		String sql = "insert into eventType(event_id, event_Name)" +
				"values(default, ?)";
		try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            // set the fields:
	            preparedStatement.setInt(1, newObj.getEventId());
	            preparedStatement.setString(2, newObj.getEventName());
	            
	            int count = preparedStatement.executeUpdate();
	            ResultSet resultSet = preparedStatement.getGeneratedKeys();
	            
	            if (count > 0) {
	            	 System.out.println("Correct Event!");
	            	 resultSet.next();
	                 int deptId = resultSet.getInt(1);
	                 newObj.setEventId(deptId);
	                 connection.commit();
	            }
	            else {
	                 System.out.println("Event not created!");
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
		return  newObj.getEventId();
	}

	public Event getById(int id) {
		Event event = null;
		
		String sql = "SELECT * FROM event WHERE id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            // because the resultSet has multiple pets in it, we don't just want an if-statement. We want a loop:
            if(resultSet.next()) {
               event = parseResultSet(resultSet);
            } else {
                System.out.println("Something went wrong when querying Event!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return event;
	}

	private Event parseResultSet(ResultSet resultSet) throws SQLException{
		Event event = new Event();
        // do something with the return value:
		event.setEventId(resultSet.getInt(1));
        event.setEventName(resultSet.getString(2));
        
        int event_id = resultSet.getInt(6);
        String status = (event_id == 1) ? "Available" : "Unavailable";
        event.setEventName(status);
		return event;
	}

	public List<Event> getAll() {
		List<Event> events = new ArrayList<Event>();
		
		String sql = "SELECT * FROM event";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            
            ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
             Event event = parseResultSet(resultSet);
            events.add(event);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return events;
	}

	public void update(Event updatedObj) throws SQLException{
		String sql = "update event set name = ?,  where id = ?;";
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	preparedStatement.setString(1,updatedObj.getEventName());
        	preparedStatement.setInt(2, updatedObj.getEventId());
        	
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
        	

	public void delete(Event objToDelete) throws SQLException{
		String sql = "delete from event where id = ?;";
		
		try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getEventId());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: no object found to update");
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
