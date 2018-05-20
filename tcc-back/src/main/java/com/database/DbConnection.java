package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DbConnection {


	public static Connection con = null;
	 
	public static Connection Conectar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tcc?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "teste12");
		return conn;
	 
	}
}
