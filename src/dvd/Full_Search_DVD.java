package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Full_Search_DVD {

	public void scdvd() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
			con = DriverManager.getConnection(url, "DVD", "1111");


			String sql = "SELECT * FROM dvd";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.print("DVD ID \t");
			System.out.print("타이틀 \t\t ");
			//System.out.print("장르 \t");
			System.out.print("나이제한 \t");
			//System.out.print("출시날짜 \t");
			System.out.println("대여여부 \t");
			//System.out.println("거래처번호");
			System.out.println("----------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.print(rs.getInt("dvd_id")+"\t\t ");
				System.out.print(rs.getString("title")+"\t\t");
				//System.out.print(rs.getString("genre")+"\t ");
				System.out.print(rs.getInt("allowed_age")+"\t ");
				//System.out.print(rs.getDate("release_date")+"\t ");
				if (rs.getString("rent_flag").equals("Y")) {
					System.out.println("대여 가능 \t");
				} else if (rs.getString("rent_flag").equals("N")) {
					System.out.println("대여 불가 \t");
				}
				//System.out.println(rs.getInt("vendor_id"));

			}


		} catch (Exception e) {
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