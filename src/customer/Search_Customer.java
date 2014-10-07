import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DB1 {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.10.21:1521:orcl";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		Statement fstmt = null;
		ResultSet frs = null;
		String fsql;
		try{
			con = DriverManager.getConnection(url, "dvd","1111"); 
			Scanner scan = new Scanner(System.in);
			while(true){
				System.out.println("고객조회창");
				System.out.print("고객명 : ");
				String name = scan.next();

				sql = "SELECT * FROM member";
				stmt = con.createStatement(); 
				rs = stmt.executeQuery(sql);
				fsql = "SELECT * FROM family";
				fstmt = con.createStatement(); 
				frs = fstmt.executeQuery(fsql);

				while(rs.next()){
					if (rs.getString(2).equals(name)){
						System.out.println("고객번호\t고객명\t\t주소\t\t\t나이\t전화번호\t가족\t\t관계");
						System.out.print(rs.getInt(1)+"\t\t");
						System.out.print(rs.getString(2)+"\t\t");
						System.out.print(rs.getString(3)+"\t");
						System.out.print(rs.getInt(4)+"\t");
						System.out.print(rs.getInt(5)+"\t");
						while(frs.next()){
							if(rs.getInt(1)==frs.getInt(1)){
								System.out.print(frs.getString(2)+"\t\t");
								System.out.print(frs.getString(3));
							}
						}
						System.out.println("");
					}
					else {
						System.out.println("검색된 고객이 없습니다.");
					}
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
	}
}

