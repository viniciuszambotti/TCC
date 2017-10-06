package com.dao;

import java.sql.*;

public class ExampleDao {

	public static boolean insterExample(Connection conn) {
	     // the mysql insert statement
	      String query = "insert into table_teste (fd1, fd2)"
	        + " values (?, ?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, "Barney");
		      preparedStmt.setString (2, "Rubble");  
		      preparedStmt.execute();		      
		      conn.close();
		      return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      return false;
	}
}
