package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.object.Analise;
import com.object.Empresa;

public class AnaliseDao {
	private final static String TABLE = "Analise";

	public static String createRecord(Connection conn, Analise analise) throws SQLException {
		String query = "insert into " + TABLE + " (nomeAnalise, nomeScript, tabelaSaida)" + " values (?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, analise.getNomeAnalise());
			preparedStmt.setString(2, analise.getNomeScript());
			preparedStmt.setString(3, analise.getTabelaSaida());
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

	public static void updateRecord(Connection conn, Analise analise) throws SQLException {
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE " + TABLE + " SET nomeAnalise = ?,nomeScript = ?, tabelaSaida= ? " + " WHERE id = ?";
		preparedStatement = conn.prepareStatement(updateTableSQL);
		preparedStatement.setString(1, analise.getNomeAnalise());
		preparedStatement.setString(2, analise.getNomeScript());
		preparedStatement.setString(3, analise.getTabelaSaida());
		preparedStatement.setInt(4, analise.getId());

		// execute update SQL stetement
		preparedStatement.executeUpdate();
		conn.close();

	}

	public static Analise findRecordById(Connection conn, int id) throws SQLException {
		Analise analise = new Analise();
		String selectSQL = "SELECT * FROM " + TABLE + " WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			analise.setNomeAnalise(rs.getString("nomeAnalise"));
			analise.setNomeScript(rs.getString("nomeScript"));
			analise.setTabelaSaida(rs.getString("tabelaSaida"));
		}
		conn.close();
		return analise;
	}

	public static List<Analise> getAll(Connection conn) throws SQLException {
		Analise analise;
		List<Analise> analises = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + TABLE;
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		ResultSet rs = preparedStatement.executeQuery(selectSQL);
		while (rs.next()) {
			analise = new Analise();
			analise.setId(rs.getInt("id"));
			analise.setNomeScript(rs.getString("nomeScript"));
			analise.setNomeAnalise(rs.getString("nomeAnalise"));
			analise.setTabelaSaida(rs.getString("tabelaSaida"));
			analises.add(analise);
		}
		conn.close();
		return analises;
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
