package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Insert_DVD{

	public  void insert_dvd() {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		Statement stmt2 = null;
		
		try{
	// 기본양식
			
			Class.forName("oracle.jdbc.OracleDriver");// 해당 클래스를 메모리로로딩
			String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl"; //경로
			con = DriverManager.getConnection(url, "dvd", "1111");  // 연결 접속정보
			
	//기본양식
			
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			
		/*	System.out.println("DVD ID :");
			int empno = sc.nextInt(); 시퀀스*/ 
			System.out.println("제목 : ");
			String Title = sc.next();
			System.out.println("장르 :");
			String genre = sc.next();
			System.out.println("관람연령 :");
			int age = sc.nextInt();
			System.out.println("출시일 : ");
			String date = sc.next();
			System.out.println("거래처번호 :");
			int vendor = sc.nextInt();
	
			
			String sql = "INSERT INTO DVD VALUES(sq_dvd_id.nextVal,?,?,?,TO_DATE(?),'Y',?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Title);
			stmt.setString(2, genre);
			stmt.setInt(3, age);
			stmt.setString(4, date);
			stmt.setInt(5, vendor);
					
			stmt.executeUpdate();
			System.out.println("등록되었습니다.");

			
			
			
		
			
		}catch(Exception e){
			e.printStackTrace();
			
		} finally{
			if(rs !=null)
				try{rs.close();}catch (Exception e) {}
			if (stmt !=null) 
				try{stmt.close();} catch (Exception e) {}
			if (con != null) 
				try {con.close();} catch (Exception e) {}
		}
		

	}

}