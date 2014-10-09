package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search_History {

	@SuppressWarnings("resource")
	public void search_history() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
			con = DriverManager.getConnection(url, "DVD", "1111");


			String sql = "SELECT * FROM managerdvd WHERE return_date is not null ORDER BY return_date desc";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("dvd id\t타이틀\t\t대여 회원 id \t\t대여일 \t\t\t\t 반납일");
			while (rs.next()) {
				System.out.print(rs.getInt("dvd_id")+"\t\t ");
				System.out.print(rs.getString("title")+"\t\t");
				System.out.print(rs.getInt("cus_id")+"\t\t\t ");
				System.out.print(rs.getString("rent_date")+"\t ");
				System.out.print(rs.getString("return_date")+"\t \n");
			}

			System.out.println("------------------------------------");
			while(true){
				System.out.println("과거 기록을 정리 하시겠습니까? (1: 정리/2. 메인메뉴");
				Scanner sc = new Scanner(System.in);
				int menu = sc.nextInt();
				if(menu ==1){
					System.out.println("기준 반납일을 입력해 주세요 해당일 이전 기록을 삭제합니다.");
					System.out.println("삭제된 기록은 다시 복구 할 수 없습니다. 주의 해 주세요");
					System.out.print("(입력 예: 140101) : ");
					String deldate = sc.next();

					sql = "DELETE FROM managerdvd WHERE reurn_date <= TO_DATE('"+deldate+"')";//시간 문제로 아마 +1 해야 될 것 같음. 테스트 필요!
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					System.out.println("삭제 되었습니다.");
					return;
				}else if(menu ==2){
					return;
				}else{
					System.out.println("정확한 번호를 입력해 주세요");
					continue;
				}

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
