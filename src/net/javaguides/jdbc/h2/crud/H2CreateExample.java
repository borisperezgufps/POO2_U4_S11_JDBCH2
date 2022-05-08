package net.javaguides.jdbc.h2.crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2CreateExample {
	

	public static void main(String[] argv) throws SQLException {
		H2CreateExample createTableExample = new H2CreateExample();
		createTableExample.createTable();
	}

	public void createTable() throws SQLException {

		StringBuffer sbCreateTableSQL = new StringBuffer();
		sbCreateTableSQL.append("create table users (id int primary key, ");
		sbCreateTableSQL.append("name varchar(20), email varchar(20), ");
		sbCreateTableSQL.append("country varchar(20), password varchar(20))");
		
		System.out.println(sbCreateTableSQL);
		// Step 1: Establishing a Connection
		try (Connection connection = H2JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				Statement statement = connection.createStatement();) {

			// Step 3: Execute the query or update query
			statement.execute(sbCreateTableSQL.toString());

		} catch (SQLException e) {
			// print SQL exception information
			e.printStackTrace();
		} finally {
//			insertRecord();
		}
	}

	public void insertRecord() throws SQLException {
		

		Connection conn = null;
		Statement statementOb = null;
		
		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO USERS(id, name, email, country, password) ");
			sb.append("VALUES (2, 'boris', 'correo', 'Colombia', '12345')");

			statementOb.executeUpdate(sb.toString());

		} catch (SQLException e) {
			// print SQL exception information
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				statementOb.close();
				conn.close();
			} catch (SQLException e) {
				// print SQL exception information
				e.printStackTrace();
			}

		}

	}
}
