package services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory connectionFactory = null;
	private static Properties properties;
	
	private ConnectionFactory() {
		InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream("dataBaseCon.properties");
		try {
			properties = new Properties();
			properties.load(stream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//return our connection to the database:
	public static ConnectionFactory getConnectionFactory() {
		//if no connection yet:
		
		if (connectionFactory == null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
	public static Connection getConnection() {
		//set up credentials (username, password)
		Connection connection = null;
			//pull these from db config file
			
			/*String username = properties.getProperty("username");
			String password = properties.getProperty("password"); 
		*/
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "081392";
		
		//try connecting to the database:
		try {
			//get connection 
			
			//register the driver
			//Class.forName("org.postgressql.Drvier"); //need this to connect
			
			connection = DriverManager.getConnection(url, username, password);
			
		}	catch (SQLException e) {
			//if something goes wrong, view the stack trace
			e.printStackTrace();
			}
		
		return connection;
	}
}
