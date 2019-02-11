package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	public static Connection getConexion() {
		Connection con = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/douglascarrental";
			String usr = "root";
			String psw = "";
			
			con = DriverManager.getConnection(url, usr, psw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver not Installed!!");
		} catch (SQLException e) {
			System.out.println("Error >> Connection error to the Database BD");
		}
		return con;
	}

}
