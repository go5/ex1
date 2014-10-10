package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rent_State {
	public void rent_state(){
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
		con = DriverManager.getConnection(url, "DVD", "1111");

	
		String sql = "SELECT * FROM managerdvd WHERE return_date is null";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		System.out.println("dvd id\t타이틀\t\t대여 회원 id \t\t대여일");
		while (rs.next()) {
			System.out.print(rs.getInt("dvd_id")+"\t\t ");
			System.out.print(rs.getString("title")+"\t\t");
			System.out.print(rs.getInt("cus_id")+"\t\t\t ");
			System.out.print(rs.getString("rent_date")+"\t ");
			System.out.prinln("");
		}
	
	
	
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
}
