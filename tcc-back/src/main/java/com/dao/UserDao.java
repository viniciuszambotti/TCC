package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.object.User;


public class UserDao {
	private final static String TABLE = "User";

	public static String createRecord(Connection conn, User user) throws SQLException {
		String query = "insert into " + TABLE + " (usuario, senha, email, tipo, cpf, fk_departamento)" + " values (?, ?, ?, ?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, user.getUsuario());
			preparedStmt.setString(2, user.getSenha());
			preparedStmt.setString(3, user.getEmail());
			preparedStmt.setString(4, user.getTipo());
			preparedStmt.setString(5, user.getCpf());
			preparedStmt.setInt(6, user.getFk_departamento());
			preparedStmt.execute();
			conn.close();
			return "Inserido com sucesso";
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.close();
			return "Falha ao inserir";

		}

	}

	public static void updateRecord(Connection conn, User user) throws SQLException {
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE " + TABLE + " SET usuario = ?,senha = ?, email= ?, tipo= ?, cpf= ?, fk_departamento= ? " + " WHERE id =" + user.getId();
		preparedStatement = conn.prepareStatement(updateTableSQL);
		preparedStatement.setString(1, user.getUsuario());
		preparedStatement.setString(2, user.getSenha());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getTipo());
		preparedStatement.setString(5, user.getCpf());
		preparedStatement.setInt(6, user.getFk_departamento());

		// execute update SQL stetement
		preparedStatement.executeUpdate();
		conn.close();

	}

	public static User findRecordById(Connection conn, int id) throws SQLException {
		User user = new User();
		String selectSQL = "SELECT * FROM " + TABLE + " WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			user.setUsuario(rs.getString("usuario"));
			user.setEmail(rs.getString("email"));
			user.setSenha(rs.getString("senha"));
			user.setTipo(rs.getString("tipo"));
			user.setCpf(rs.getString("cpf"));
			user.setFk_departamento(rs.getInt("fk_departamento"));
		}
		conn.close();
		return user;
	}
	public static User findUserPsw(Connection conn, User usr) throws SQLException {
		String selectSQL = "SELECT * FROM " + TABLE + " WHERE email ='" + usr.getEmail() + "' AND senha='" + usr.getSenha() + "'";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		
		ResultSet rs = preparedStatement.executeQuery(selectSQL);

		User user = new User();
		while (rs.next()) {
			user.setUsuario(rs.getString("usuario"));
			user.setEmail(rs.getString("email"));
			user.setSenha(rs.getString("senha"));
			user.setTipo(rs.getString("tipo"));
			user.setCpf(rs.getString("cpf"));
			user.setFk_departamento(rs.getInt("fk_departamento"));
		}
		conn.close();
		return user;
	}

	public static List<User> getAll(Connection conn) throws SQLException {
		User user;
		List<User> users = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + TABLE;
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUsuario(rs.getString("usuario"));
			user.setEmail(rs.getString("email"));
			user.setSenha(rs.getString("senha"));
			user.setTipo(rs.getString("tipo"));
			user.setCpf(rs.getString("cpf"));
			user.setFk_departamento(rs.getInt("fk_departamento"));
			users.add(user);
		}
		conn.close();
		return users;
	}

	public static void deleteRecord(Connection conn, int id) throws SQLException {
		String deleteSQL = "DELETE from " + TABLE + " WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
		System.out.println(deleteSQL);
		preparedStatement.setInt(1, id);
		// execute delete SQL stetement
		preparedStatement.executeUpdate();
	}

}
