package dvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Return_DVD {
	@SuppressWarnings("resource")
	public void return_dvd(){
		Connection con = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;


		try{
			Class.forName("oracle.jdbc.OracleDriver");

			String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
			con = DriverManager.getConnection(url,"DVD","1111");

			Scanner scan = new Scanner(System.in);

			System.out.print("반납할 dvd id를 입력하세요: ");

			int dvd_id = scan.nextInt();
			//반납할 디비디를 찾아봄 반납 날짜가 null인 디비디 제목을 검색.
	//반납 확인 부
			String sh_title = "SELECT title from managerDVD where dvd_id=? and return_date is null";				
			stmt=con.prepareStatement(sh_title); 
			stmt.setInt(1,dvd_id); 
			rs = stmt.executeQuery();
			if(!rs.next()){
				System.out.println("id를 다시 확인해 주세요");
				return;
			}

	//반납 처리부
			//디비디를 반납할 때 반납날짜는 현재 날짜로 수정됨
			String return_date= "Update managerDVD set return_date=sysdate where dvd_id=? and return_date is null";
			//연체 날짜를 알아봄.
			stmt1=con.prepareStatement(return_date); 
			stmt1.setInt(1,dvd_id); 
			stmt1.executeUpdate();

			String overdue_date= "Update managerDVD set overdue_date=TO_NUMBER(return_date-5-rent_date) where dvd_id=?";
			stmt2=con.prepareStatement(overdue_date); 
			stmt2.setInt(1,dvd_id); 
			stmt2.executeUpdate();

			String overdue_cost="Update managerDVD set overdue_cost=TO_NUMBER(overdue_date)*100 where dvd_id=?";
			stmt3=con.prepareStatement(overdue_cost); 
			stmt3.setInt(1,dvd_id); 
			stmt3.executeUpdate();

			String rent_flag="Update DVD set rent_flag='Y' where dvd_id=?";
			stmt4=con.prepareStatement(rent_flag); 
			stmt4.setInt(1,dvd_id); 
			stmt4.executeUpdate();


			String bannab="SELECT * FROM MANAGERDVD where dvd_id = ? AND return_date = (SELECT max(return_date) from managerdvd where dvd_id=?)";

			stmt=con.prepareStatement(bannab); 
			stmt.setInt(1,dvd_id); 
			stmt.setInt(2,dvd_id); 
			rs = stmt.executeQuery();
			while(rs.next()){
//				System.out.println(rs.getString("RETURN_DATE"));
				int cost = rs.getInt("OVERDUE_COST");
				int date = rs.getInt("OVERDUE_date");
				if(date<0){
					cost = 0;
					date = 0;
				}else{
					cost = cost-(cost%100);
				}
				System.out.print(rs.getString("title")+"(이/가) 반납되었습니다. 연체일은 "+ date + "일 이고, 연체료는 "+cost+"원 입니다.");
			}
		}
		catch(Exception err){
		}	
		finally{

			if(rs != null) try{rs.close();} catch(Exception err){}	
			if(stmt != null) try{stmt.close();} catch(Exception err){}
			if(stmt1 != null) try{stmt1.close();} catch(Exception err){}
			if(stmt2 != null) try{stmt2.close();} catch(Exception err){}
			if(stmt3 != null) try{stmt3.close();} catch(Exception err){}
			if(stmt4 != null) try{stmt4.close();} catch(Exception err){}
			if(con != null)	try{con.close();} catch(Exception err){}
		}
	}
}