package net.javaguides.jdbc.h2.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class H2CreateExample {

	public static void main(String[] argv) throws SQLException {
		H2CreateExample h2Example = new H2CreateExample();
		h2Example.createTable();
		
		String valId = JOptionPane.showInputDialog("Ingrese el ID: ");
		String valName = JOptionPane.showInputDialog("Ingrese el Nombre: ");
		String valEmail = JOptionPane.showInputDialog("Ingrese el Email: ");
		String valCountry = JOptionPane.showInputDialog("Ingrese el Pais: ");
		String valContra = JOptionPane.showInputDialog("Ingrese el Contrasena: ");
		
		h2Example.insertRecord(valId, valName, valEmail, valCountry, valContra);
		h2Example.showRecords();
		
		
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
			System.err.println("Ya está creada la tabla");
		} finally {
//			insertRecord();
		}
	}

	public void insertRecord(String id, String name, String email, String country, String contra) throws SQLException {

		Connection conn = null;
		Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO USERS(id, name, email, country, password) ");
			sb.append("VALUES ("+id+", '"+name+"', '"+email+"', '"+country+"', '"+contra+"')");

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
	
	public void updateRecord(String id, String name, String email, String country, String contra) throws SQLException {

		Connection conn = null;
		Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE USERS SET name = '"+name+"', email = '"+email+"', country='"+country+"', password='"+contra+"' ");
			sb.append("WHERE id="+id+"");

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
	
	public void deleteRecord(String id) throws SQLException {

		Connection conn = null;
		Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			StringBuffer sb = new StringBuffer();
			sb.append("DELETE FROM USERS WHERE id="+id+"");

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

	public void showRecords() throws SQLException {

		Connection conn = null;
		Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			String sql = "SELECT id, name, email, country FROM users";
			ResultSet rs = statementOb.executeQuery(sql);
			
			while(rs.next()) { 
	            // Retrieve by column name 
	            int id  = rs.getInt("id"); 
	            String name = rs.getString("name"); 
	            String email = rs.getString("email"); 
	            String country = rs.getString("country");  
	            
	            // Display values
	            System.out.println("User "+ id); 
	            System.out.println("----------------------");
	            System.out.println("Name: " + name); 
	            System.out.println("Email: " + email); 
	            System.out.println("Country: " + country);
	            System.out.println();
	         } 
			
			rs.close();

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
