package com.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DbConnection {
	public static String status = "Não conectou...";

	//Método de Conexão//

	public static java.sql.Connection getConexaoMySQL() {

		Connection connection = null;          //atributo do tipo Connection



		try {

			// Carregando o JDBC Driver padrão

			String driverName = "com.mysql.jdbc.Driver";                        

			Class.forName(driverName);



			// Configurando a nossa conexão com um banco de dados//

			String serverName = "localhost";    //caminho do servidor do BD

			String mydatabase ="testando";        //nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root";        //nome de um usuário de seu BD      

			String password = "Vini@121095";      //sua senha de acesso

			connection =  (Connection) DriverManager.getConnection(url, username, password);

			//Testa sua conexão//  

			if (connection != null) {

				status = ("STATUS--->Conectado com sucesso!");

			} else {

				status = ("STATUS--->Não foi possivel realizar conexão");

			}
	    	System.out.println(DbConnection.statusConection());

			return connection;

		} catch (ClassNotFoundException e) {  //Driver não encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

			//Não conseguindo se conectar ao banco

			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;

		}
	}
	
	//Método que retorna o status da sua conexão//

	public static String statusConection() {

		return status;

	}
	//Método que fecha sua conexão//

	public static boolean FecharConexao() {

		try {

			DbConnection.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}
}
