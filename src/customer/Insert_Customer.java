package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Insert_Customer{

	public void insert_Customer() {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Statement stmtt =null;

		try{
			// 기본양식

			Class.forName("oracle.jdbc.OracleDriver");// 해당 클래스를 메모리로로딩
			String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl"; //경로
			con = DriverManager.getConnection(url, "dvd", "1111");  // 연결 접속정보

			//기본양식


			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			System.out.println("이름 : ");
			String name = sc.next();
			System.out.println("주소 :");
			String address = sc.next();
			System.out.println("나이 :");
			int ages = sc.nextInt();
			System.out.println("연락처 : ");
			String phone_num = sc.next();


			String sql = "INSERT INTO MEMBER VALUES(sq_cus_id.nextVal,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, address);
			stmt.setInt(3, ages);
			stmt.setString(4, phone_num);

			stmt.executeUpdate();
			System.out.println("등록되었습니다.");
 
			
			System.out.println("가족멤버를 추가하시겠습니까?(y/n)");
			String check = sc.next();
			if(check.equalsIgnoreCase("y")){
				sql = "SELECT SQ_CUS_ID.currval FROM dual";
				stmtt = con.createStatement();
				rs= stmtt.executeQuery(sql);
				rs.next();
				Insert_Family iif = new  Insert_Family();
				iif.insert_family(rs.getInt(1));
			}else{
				return;}


			
			


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


