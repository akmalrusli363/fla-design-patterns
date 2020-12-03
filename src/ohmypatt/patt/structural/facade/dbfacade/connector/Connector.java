package ohmypatt.patt.structural.facade.dbfacade.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
	private Connection connection;

	private String connection_DB = "jdbc:mysql://localhost:3306/restoku";
	private String user = "root";
	private String password = "";

	public Connector() {
		try {
			// Use MySQL JDBC Driver to connect to MySQL Database
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(connection_DB, user, password);
			System.out.println("Connected to SQL Services!");
		} catch (ClassNotFoundException e) {
			System.err.println("No MySQL Driver found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Error Occurred!");
			e.printStackTrace();
		}
	}

	public PreparedStatement createStatement(String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	public int executeUpdate(PreparedStatement statement) throws SQLException {
		int rowsAffected = statement.executeUpdate();
		return rowsAffected;
	}

	public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
		return statement.executeQuery();
	}
}