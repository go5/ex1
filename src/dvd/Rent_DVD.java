package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Rent_DVD {

	public static void rent_DVD() {
		Connection con = null;

		PreparedStatement stmtRent = null; // DVD 렌트

		PreparedStatement stmtgetDvd = null; // dvd테이블에서 렌트여부 확인
		ResultSet rsgetDvd = null; // dvd테이블에서 렌트여부 확인

		PreparedStatement stmtDvdsearch = null; // dvd id검색
		ResultSet rsDvdsearch = null; // dvd id검색

		PreparedStatement stmtgetCus = null; // 고객 테이블에 고객ID가 있는 지 확인
		ResultSet rsgetCus = null; // 고객 테이블에 고객ID가 있는 지 확인

		PreparedStatement stmtcusCnt = null; // 고객 대여가 3이상인지 확인
		ResultSet rscusCnt = null; // 고객 대여가 3이상인지 확인

		PreparedStatement stmtrentflag = null; // dvd 테이블에 rent_flag업데이트

		String getDvd = null; // dvd 대여 여부 확인 변수
		String getCus = null; // 고객 null확인용 변수
		String getcusCnt = null; // 고객 대여 한도 확인 변수
		Scanner sc = new Scanner(System.in);

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "jointest", "1111");

			// 고객아이디 null값 조회
			System.out.println("************* DVD대여 *************\n");
			System.out.println("고객ID를 조회하세요");
			String cusID = sc.next();

			getCus = "SELECT cus_id FROM cu WHERE cus_id=?";
			stmtgetCus = con.prepareStatement(getCus);
			stmtgetCus.setString(1, cusID);
			rsgetCus = stmtgetCus.executeQuery();
			// 고객 아이디 null값 끝

			// 고객 아이디 카운트 확인
			getcusCnt = "SELECT count(*) FROM managerDVD WHERE cus_id=? AND return_date=NULL ";
			stmtcusCnt = con.prepareStatement(getcusCnt);
			stmtcusCnt.setString(1, cusID);
			rscusCnt = stmtcusCnt.executeQuery();
			// 고객 대여 카운트 확인 끝

			rscusCnt.next();

			if (rsgetCus.next()) {

				int cusId = rsgetCus.getInt("cus_id");
				int cusCnt = rscusCnt.getInt(1);

				if (cusId != 0) {// 고객ID null확인 시작
					System.out.println("고객정보가 있습니다");

					if (cusCnt < 4) { // 고객 대여 한도 확인 가능하면 DVD렌트 여부 확인.
						System.out.println("대여가 가능합니다.");

						System.out.println("대여하려는 DVD의 ID를 입력하세요.");
						String dvdID = sc.next();

						String searchDVDid = "SELECT dvd_id FROM dvd WHERE dvd_id=?";
						stmtDvdsearch = con.prepareStatement(searchDVDid);
						stmtDvdsearch.setString(1, dvdID);
						rsDvdsearch = stmtDvdsearch.executeQuery();

						if (rsDvdsearch.next()) {

							// DVD 대여여부 확인
							getDvd = "SELECT rent_flag, title FROM dvd WHERE dvd_id=?";
							stmtgetDvd = con.prepareStatement(getDvd);
							stmtgetDvd.setString(1, dvdID);
							rsgetDvd = stmtgetDvd.executeQuery();
							// DVD대여여부 확인 끝

							rsgetDvd.next();
							String dvdRent = rsgetDvd.getString("rent_flag");
							String dvdTitle = rsgetDvd.getString("title");

							if (dvdRent.equals("Y")) {

								System.out.println("DVD 재고 있습니다");
								System.out.println("타이틀 이름은 " + dvdTitle
										+ " 입니다");

								while (true) {

									System.out
											.println("대여 하시겠습니까? 1번 : 대여 / 2번 : 아니오");
									int rentOrNo = sc.nextInt();

									if (rentOrNo == 1) {
										System.out.println("현재 고객님의 ID는 "
												+ cusId + " 이고, DVD ID는 "
												+ dvdID + " 입니다");

										System.out.println("대여료 : ");
										int rent_cost = sc.nextInt();

										String rent = "INSERT INTO managerDVD(dvd_id, cus_id, title, rent_cost,rent_date) "
												+ "VALUES (?,?,?,?,sysdate)";

										stmtRent = con.prepareStatement(rent);
										stmtRent.setString(1, dvdID);
										stmtRent.setInt(2, cusId);
										stmtRent.setString(3, dvdTitle);
										stmtRent.setInt(4, rent_cost);

										stmtRent.executeUpdate();

										String rentFlag = "UPDATE dvd SET rent_flag='N' WHERE dvd_id=?";
										stmtrentflag = con
												.prepareStatement(rentFlag);
										stmtrentflag.setString(1, dvdID);
										stmtrentflag.executeUpdate();

										System.out.println(dvdTitle
												+ "대여 되었습니다. ");
										System.out
												.println("대여일을 5일이고, 연체료는 1일 100원입니다.");

										break;
									} else if (rentOrNo == 2) {
										break;
									} else {
										System.out.println("1,2 만 입력하세요.");
									}

								}

							} else if (dvdRent.equals("n")) {
								System.out.println("DVD가 대여중입니다.");
							}

						} else if (!rsDvdsearch.next()) {
							System.out.println("DVD 정보가 없습니다.");

						}// 대여 한도 가능
						else if (cusCnt > 3) {
							System.out.println("DVD대여 한도를 초과하였습니다.");
						}// 대여 한도 불가 대여한도 확인 끝
					}
				}

			} else if (!rsgetCus.next()) {
				System.out.println("고객정보가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에..에러...몰라..ㅠㅠㅠㅠㅠㅠㅠㅠ");

		} finally {
			if (rsgetDvd != null) {try {rsgetDvd.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmtRent != null) {try {stmtRent.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmtgetDvd != null) {try {stmtgetDvd.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmtgetCus != null) {try {stmtgetCus.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmtcusCnt != null) {try {stmtcusCnt.close();} catch (SQLException e) {e.printStackTrace();}}
			if (stmtrentflag != null) {try {stmtrentflag.close();} catch (SQLException e) {e.printStackTrace();}}
			if (con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}

		}

	}

}
}
