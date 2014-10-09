package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Search_Customer {
	@SuppressWarnings("resource")
	public void search_Customer(){
		String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		Statement fstmt = null;
		ResultSet frs = null;
		String fsql;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "dvd","1111"); 
			Scanner scan = new Scanner(System.in);
			System.out.println("고객조회창");
			System.out.print("전화번호조회 : ");
			String phone_num = scan.next();

			sql = "SELECT * FROM member"; 
			stmt = con.createStatement(); 
			rs = stmt.executeQuery(sql);
			
			fsql = "SELECT * FROM family";
			fstmt = con.createStatement(); 
			frs = fstmt.executeQuery(fsql);
			
			

			while(rs.next()){
				if (rs.getString(5).equals(phone_num)){
					System.out.println("고객번호\t고객명\t\t주소\t\t나이\t전화번호\t가족(관계)");
					int id = rs.getInt(1);
					System.out.print(rs.getInt(1)+"\t\t");
					System.out.print(rs.getString(2)+"\t\t");
					System.out.print(rs.getString(3)+"\t\t");
					System.out.print(rs.getInt(4)+"\t");
					System.out.print(rs.getString(5)+"\t");

					while(frs.next()){
						if(rs.getInt(1)==frs.getInt(1)){
							System.out.print(frs.getString(2)+"(");
							System.out.print(frs.getString(3)+")\t");
						}else
							break;
					}

					System.out.println();
					//대여 이력 떠야 함. 
					sql = "SELECT * FROM managerdvd WHERE cus_id = "+id; 
					stmt = con.createStatement(); 
					rs = stmt.executeQuery(sql);
					System.out.println("대여 이력");
					System.out.println("비디오 타이틀 \t\t 대여 일자 \t\t반납일자");
					while(rs.next()){
						System.out.print(rs.getString("title")+"\t\t");
						System.out.print(rs.getString("rent_date")+"\t\t");
						System.out.print(rs.getString("return_date"));
						System.out.println();
					}
					return;
					
				}
				else {
					System.out.println("검색된 고객이 없습니다.");
					return; //회원 수만큼 에러나는 거니까 이 부분만 추가하면 될 듯.? 테스트 필요.
				}
			}
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			if(frs != null) try{frs.close();}catch(Exception err){}
			if(rs != null) try{rs.close();}catch(Exception err){}
			if(fstmt != null) try{fstmt.close();}catch(Exception err){}
			if(stmt != null) try{stmt.close();}catch(Exception err){}
			if(con != null) try{con.close();}catch(Exception err){}
		}
		return;
	}
}