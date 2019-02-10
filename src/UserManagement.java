import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utils.MySQLConexion;

public class UserManagement {

	
	public User getUser(User usu) {

		User user = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			con = MySQLConexion.getConexion();

			String sql = "select*from users where email = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, usu.getEmail());
			pst.setString(2, usu.getPassword());

			rs = pst.executeQuery();

			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2));			
				
			}
		} catch (Exception e) {
			System.out.println("Error>> Getting User");
			System.out.println(user);
		}

		return user;
	}
	
}
