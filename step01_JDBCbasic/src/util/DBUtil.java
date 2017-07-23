package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	static{ // 드라이버 로딩은 단 한번만.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{ // 예외 상황을 client에게 던져서 신속한 처리 유도
		return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
		//property를 써서 더이상 자바 코드를 변경하지 않는게 좋음.
		
	}
	
	// query = select용 자원반환
	public static void close(Connection con, Statement stmt, ResultSet rset){ // 굳이 던지지 않아도 됨. 서버가 알아서 처리.
		try { // 자원 반환 순서 매우 중요, 가장 최근 만든 것 부터 반환
			if(rset != null){
				rset.close();
				rset = null; 
			}
			if(stmt != null){
				stmt.close();
				stmt = null; 
			} 
			if(con != null){
				con.close();
				con = null; 
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// dml용 자원반환
	public static void close(Connection con, Statement stmt){ // 퍼포먼스 고려해서 dml용 따로 만듬.
		try { 
			if(stmt != null){
				stmt.close();
				stmt = null; 
			} 
			if(con != null){
				con.close();
				con = null; 
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
