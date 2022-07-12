package authentication;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

	public Connection getConnection() {
	
		 Connection conn = null;
	        
	        try
			{
			  Class.forName("org.postgresql.Driver");
			  //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bcas_opsdb", "postgres", "postgres");
			  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/surveydb?currentSchema=public", "postgres", "postgres");
			
			  return conn;
		    } 
	        catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
