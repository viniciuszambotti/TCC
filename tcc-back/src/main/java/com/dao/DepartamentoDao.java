package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.object.Departamento;

public class DepartamentoDao {
	private final static String TABLE = "Departamento";

	public static Departamento createRecord(Connection conn, Departamento departamento) throws SQLException {
		String query = "insert into " + TABLE + " (nome, fk_empresa, analises)" + " values (?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, departamento.getNome());
			preparedStmt.setInt(2, departamento.getFk_empresa());
			preparedStmt.setString(3, departamento.getAnalises());
			preparedStmt.execute();
			ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
                departamento.setId(generatedKeys.getInt(1));
            }
			return departamento;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.close();
			return null;

		}

	}

	public static void updateRecord(Connection conn, Departamento departamento) throws SQLException {
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE " + TABLE + " SET nome = ?,fk_empresa = ?, analises = ? " + " WHERE id = ?";
		preparedStatement = conn.prepareStatement(updateTableSQL);
		preparedStatement.setString(1, departamento.getNome());
		preparedStatement.setInt(2, departamento.getFk_empresa());
		preparedStatement.setString(3, departamento.getAnalises());
		preparedStatement.setInt(4,departamento.getId());

		// execute update SQL stetement
		preparedStatement.executeUpdate();
		conn.close();

	}

	public static Departamento findRecordById(Connection conn, int id) throws SQLException {
		Departamento departamento = new Departamento();
		String selectSQL = "SELECT * FROM " + TABLE + " WHERE id = " + id;
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			departamento.setNome(rs.getString("nome"));
			departamento.setFk_empresa(rs.getInt("fk_empresa"));
			departamento.setAnalises(rs.getString("analises"));
		}
		conn.close();
		return departamento;
	}

	public static List<Departamento> getAll(Connection conn) throws SQLException {
		Departamento departamento;
		List<Departamento> departamentos = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + TABLE;
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			departamento = new Departamento();
			departamento.setId(rs.getInt("id"));
			departamento.setNome(rs.getString("nome"));
			departamento.setFk_empresa(rs.getInt("fk_empresa"));
			departamento.setAnalises(rs.getString("analises"));
			departamentos.add(departamento);
		}
		conn.close();
		return departamentos;
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
