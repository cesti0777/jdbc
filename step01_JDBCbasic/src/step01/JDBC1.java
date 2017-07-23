/*
 * 1. JDBC?
 * 		- Java DataBase Connectivity
 * 		- DB 종류와 무관하게 DB연동 표준 API
 * 
 *	2. 개발을 위한 환경 설정
 *		- db별 driver를 제공
 *		- 제공하는 주 목적 : DB를 편리하게 사용 가능하게 벤더사가 제공 
 *
 * 3. 개발 단계
 * 		1단계 : driver를 메모리에 로딩, 한번만 로딩하여 공유
 * 		2단계 : 접속 객체 생성(Connection), 공유하면 안됨
 * 		3단계 : sql문장들 실행(Statement or PreparedStatement)
 * 					ResultSet executeQuery(String select){}
 * 		4단계 : 실행 결과값 활용
 * 					ResultSet의 주요 API
 * 						1. boolean next() : 검색된 다음 row가 존재하나 확인
 * 						2. 타입 getXxx("컬럼명" or 검색된 컬럼의 위치번호)
 * 							: 해당 컬럼의 데이터값 반환
 * 		5단계 : db 접속 해제와 같은 자원 반환
 */
package step01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC1 {
	public static void main(String args[]){
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			//System.err.println(con); //oracle.jdbc.driver.T4CConnection@4d76f3f8 주소 나오면 접속 성공 
												       //null나오면 접속 실패			
			stmt = con.createStatement();
			rset = stmt.executeQuery("select * from dept");
			while(rset.next()){
				System.out.println(rset.getInt("deptno") + " "
												+rset.getString(2) + " "
												+rset.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			}// end of catch
		}// end of finally
	}
}















