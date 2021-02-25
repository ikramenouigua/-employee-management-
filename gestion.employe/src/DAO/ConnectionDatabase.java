package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ConnectionDatabase {
	private static final  String url = "jdbc:postgresql://localhost:5432/gestion_employe";
    private  static final String login = "postgres";
    private static final String password = "ikrame123";
	public static Connection connect()  {
		// TODO Auto-generated method stub
		
		    try {
		      Class.forName("org.postgresql.Driver");
		      

		      

		      Connection conn = DriverManager.getConnection(url, login, password);
		      return conn;
		         
		    } catch ( Exception e) {
		    	e.printStackTrace();
	            return null;

		    }
	  }


}
