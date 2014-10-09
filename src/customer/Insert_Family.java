package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert_Family {
	public void insert_family(int input){

		Scanner sc = new Scanner(System.in);
		String scsql, add,url, famName,famState, sql;
		PreparedStatement pstmt=null;
		Connection con = null;
		ResultSet rs =null;
		int inID=input;
		boolean flag=true;


		try {
			Class.forName("oracle.jdbc.OracleDriver");
			url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
			con = DriverManager.getConnection(url, "dvd", "1111");

			//System.out.print("가족관계를 입력할 회원 id: ");
			//inID = sc.nextInt();

			scsql = "SELECT cus_id FROM member WHERE cus_id = ?";
			pstmt = con.prepareStatement(scsql);
			pstmt.setInt(1, inID);
			rs = pstmt.executeQuery(); 

			if(rs.next()){
			while(flag){		
					System.out.print("가족 이름: ");
					famName = sc.next();
					System.out.print("가족 관계: ");
					famState = sc.next();
					sql = "INSERT INTO family Values(?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, inID);
					pstmt.setString(2, famName);
					pstmt.setString(3, famState);
					pstmt.executeUpdate();

					System.out.print("입력할 가족이 더 있습니까?(y/n):  ");
					add=sc.next();
					switch(add){
					case "y" :
					case "Y" :
						flag=true;
						continue;
					case "n" :
					case "N" :
						flag=false;
						break;
					}
				}

			}else{
				System.out.println("회원 아이디를 확인해 주십시요");
			}
		

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){ try{rs.close();}catch(Exception e){}}
			if(con!=null){ try{con.close();}catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close();}catch(Exception e){}}
		}
	}


}
