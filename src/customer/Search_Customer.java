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
		Statement sstmt, stmt = null;
		ResultSet rrs, rs = null;
		String ssql, sql;
		Statement fstmt = null;
		ResultSet frs = null;
		String fsql;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, " dvd","1111"); 
			Scanner scan = new Scanner(System.in);
			System.out.println("고객조회창");
			System.out.print("전화번호조회 : ");
			String phone_num = scan.next();

			sql = "SELECT * FROM member WHERE phone_num ='"+phone_num+"'"; 
			stmt = con.createStatement(); 
			rs = stmt.executeQuery(sql);


			while(rs.next()){
				//System.out.println(sql);
				
				//고객정보 출력
				System.out.println("고객번호\t고객명\t\t주소\t\t나이\t전화번호\t가족(관계)");
				int id = rs.getInt(1);
				System.out.print(rs.getInt(1)+"\t\t");
				System.out.print(rs.getString(2)+"\t\t");
				System.out.print(rs.getString(3)+"\t\t");
				System.out.print(rs.getInt(4)+"\t");
				System.out.print(rs.getString(5)+"\t");

				fsql = "SELECT * FROM family WHERE cus_id="+id;
				fstmt = con.createStatement(); 
				frs = fstmt.executeQuery(fsql);
				
				ssql = "SELECT * FROM managerdvd WHERE cus_id = "+id; 
				sstmt = con.createStatement(); 
				rrs = sstmt.executeQuery(ssql);

				//가족 멤버 출력
				while(frs.next()){
					System.out.print(frs.getString(2)+"(");
					System.out.print(frs.getString(3)+")\t");
				}
				System.out.println();

				//대여 이력 출력
				System.out.println("대여 이력");
				System.out.println("비디오 타이틀 \t\t 대여 일자 \t\t반납일자");
				while(rrs.next()){
					System.out.print(rrs.getString("title")+"\t\t");
					System.out.print(rrs.getString("rent_date")+"\t\t");
					System.out.print(rrs.getString("return_date"));
					System.out.println();
				}
				System.out.println("-------------------------------------------");
		//		System.out.println("검색 종료");
				//break;


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