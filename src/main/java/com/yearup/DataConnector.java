package com.yearup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DataConnector {
		String userName;
		String password;
		String dbms = "mysql";
		String serverName = "localhost";
		String portNumber = "3306";
		String dbName = "budgettracker";
		public DataConnector() {
			String classpathLocation = "com/yearup/resources/authentication.txt";
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathLocation);
			try {
				String[] content = this.readFile(input).split("\n");
				this.userName = content[0];
				System.out.println(userName);
				this.password = content[1];
				System.out.println(password);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		public Connection getConnection() throws SQLException {

		    Connection conn = null;
		    Properties connectionProps = new Properties();
		    connectionProps.put("user", this.userName);
		    connectionProps.put("password", this.password);

		    if (this.dbms.equals("mysql")) {
		        conn = DriverManager.getConnection(
		                   "jdbc:" + this.dbms + "://" +
		                   this.serverName +
		                   ":" + this.portNumber + "/",
		                   connectionProps);
		    } else if (this.dbms.equals("derby")) {
		        conn = DriverManager.getConnection(
		                   "jdbc:" + this.dbms + ":" +
		                   this.dbName +
		                   ";create=true",
		                   connectionProps);
		    }
		    System.out.println("Connected to database");
		    return conn;
		}
		
		public String readFile(InputStream stream) throws IOException {      
	        String str = "";
	        StringBuffer buf = new StringBuffer();
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	            if (stream != null) {                            
	                while ((str = reader.readLine()) != null) {    
	                    buf.append(str + "\n" );
	                }  
	                return buf.toString();
	            }
	        } finally {
	            try { stream.close(); } catch (Throwable ignore) {}
	        }
	        return "";
	    }
}
