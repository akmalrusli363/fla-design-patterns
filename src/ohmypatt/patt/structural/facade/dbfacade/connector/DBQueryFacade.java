package ohmypatt.patt.structural.facade.dbfacade.connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DBQueryFacade {
	private Connector connector;

	public DBQueryFacade(Connector connector) {
		this.connector = connector;
	}

	public ResultSet select(String table) throws SQLException {
		String selectQuery = String.format("SELECT * FROM %s", table);
		PreparedStatement statement = connector.createStatement(selectQuery);
		return connector.executeQuery(statement);
	}

	public ResultSet select(String table, String[] fields) throws SQLException {
		String selectQuery = String.format("SELECT %s FROM %s", table, Arrays.toString(fields));
		PreparedStatement statement = connector.createStatement(selectQuery);
		return connector.executeQuery(statement);
	}

	public ResultSet select(String table, String[] fields, String criteria) throws SQLException {
		String selectQuery = String.format("SELECT %s FROM %s WHERE %s", table, Arrays.toString(fields), criteria);
		PreparedStatement statement = connector.createStatement(selectQuery);
		return connector.executeQuery(statement);
	}

	public int insert(String table, String[] fields) throws SQLException {
		// TODO: insert statements
		return -1;
	}

	public int update(String table, String updateField, String criteria) throws SQLException {
		String updateQuery = String.format("UPDATE %s SET %s = ? WHERE %s", table, updateField, criteria);
		PreparedStatement statement = connector.createStatement(updateQuery);
		return connector.executeUpdate(statement);
	}

	public int delete(String table, String criteria) throws SQLException {
		String updateQuery = String.format("DELETE FROM %s WHERE %s", table, criteria);
		PreparedStatement statement = connector.createStatement(updateQuery);
		return connector.executeUpdate(statement);
	}
}