package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

 class Search_DVD {

	public static void scdvd() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "jointest", "1111");

			Scanner sc = new Scanner(System.in);
			System.out.println("DVD제목 입력 : ");
			String title = sc.next();

			String sql = "SELECT * FROM dvd WHERE title=?";

			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("DVD ID : \t" + rs.getInt("dvd_id"));
				System.out.println("타이틀 : \t" + rs.getString("title"));
				System.out.println("장르 : \t" + rs.getString("genre"));
				System.out.println("나이제한 : \t" + rs.getInt("allowed_age"));
				System.out.println("출시날짜 : \t" + rs.getDate("release_date"));
				if (rs.getString("rent_flag").equals("n")) {
					System.out.println("대여여부 : \t" + "대여 가능");
				} else if (rs.getString("rent_flag").equals("y")) {
					System.out.println("대여여부 : \t" + "대여 불가");
				}
				System.out.println("거래처번호 : " + rs.getInt("vendor_id"));

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