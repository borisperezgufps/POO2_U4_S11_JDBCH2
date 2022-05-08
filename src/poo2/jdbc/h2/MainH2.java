package poo2.jdbc.h2;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainH2 {

	public static void main(String[] args) throws Exception{
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		
		System.out.println(conn);
		
		conn.close();
	}

	

}
