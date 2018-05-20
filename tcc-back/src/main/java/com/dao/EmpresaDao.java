package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.object.Empresa;

public class EmpresaDao {
	private final static String TABLE = "Empresa";

	public static String createRecord(Connection conn, Empresa empresa) throws SQLException {
		String query = "insert into " + TABLE + " (nome, cnpj)" + " values (?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, empresa.getNome());
			preparedStmt.setString(2, empresa.getCnpj());
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

	public static void updateRecord(Connection conn, Empresa empresa) throws SQLException {
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE " + TABLE + " SET nome = ?,cnpj = ? " + " WHERE id = ?";
		preparedStatement = conn.prepareStatement(updateTableSQL);
		preparedStatement.setString(1, empresa.getNome());
		preparedStatement.setString(2, empresa.getCnpj());
		preparedStatement.setInt(3, empresa.getId());

		// execute update SQL stetement
		preparedStatement.executeUpdate();
		conn.close();

	}

	public static Empresa findRecordById(Connection conn, int id) throws SQLException {
		Empresa empresa = new Empresa();
		String selectSQL = "SELECT * FROM " + TABLE + " WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			empresa.setNome(rs.getString("nome"));
			empresa.setCnpj(rs.getString("cnpj"));
		}
		conn.close();
		return empresa;
	}

	public static List<Empresa> getAll(Connection conn) throws SQLException {
		Empresa empresa;
		List<Empresa> empresas = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + TABLE;
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			empresa = new Empresa();
			empresa.setId(rs.getInt("id"));
			empresa.setNome(rs.getString("nome"));
			empresa.setCnpj(rs.getString("cnpj"));
			empresas.add(empresa);
		}
		conn.close();
		return empresas;
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
