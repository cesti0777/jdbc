/*
 *  학습내용
 *  1. 각 메소드별로 기능 구현
 *  	select(전체, 부서번호로 해당 부서만)
 *  	insert / update / delete
 *  
 *  2. 효율적인 코드 구현
 */
package step01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.domain.DeptDTO;
import util.DBUtil;

public class DeptDAO {
	
	//부서 번호로 해당 부서의 모든 정보 검색
	//검색해서 콘솔 출력? 반환해서 요청곳으로 데이터 제공후 ...
	/*
	 * 경우의 수 
	 * 1. SQLException
	 * 2. 정상 실행
	 * 		1. 데이터 존재 = DeptDTO
	 * 		2. 데이터 미 존재 - null
	 */
	static DeptDTO selectDept(int deptno) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		try{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rset = stmt.executeQuery("select * from dept where deptno=" + deptno);
			if(rset.next()){
				return new DeptDTO(rset.getInt(1), rset.getString(2), rset.getString(3));
			}
		}finally {
			DBUtil.close(con, stmt, rset);
		}
		return null;
	}
	
	//모든 부서 정보 검색
	/*
	 * 1. SQLException
	 * 		- DB접속 문제, sql문장 오류
	 * 
	 * 2. table존재 및 데이터 존재
	 * 		- ArrayList를 view로 출력
	 * 
	 * 3. table존재 데이터 무
	 * 		- ArrayList 객체는 존재, 내용이 size() 0
	 */
	static ArrayList<DeptDTO> selectAll() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<DeptDTO> all = null;
		try{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rset = stmt.executeQuery("select * from dept");
			
			all = new ArrayList<DeptDTO>();//여기서 생성하는 이유는 위에 con, stmt, rset에서 요류가 나도 생성하지 않은 상태라
																//쓰래기 변수가 되지 않음.
			while(rset.next()){
				all.add(new DeptDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
		}finally {
			DBUtil.close(con, stmt, rset);
		}
		return all;
	}
	
	/*
	 * insert 시도시 발생 가능한 경우의 수
	 * 1. 정상적으로 insert
	 * 2. 존재하는 deptno와 중복, 무결성 에러 발생
	 * 		- SQLException 발생
	 */
	static void insertDept(DeptDTO dept) throws SQLException{
		Connection con = null;
		// 하나의 이 객체로 다수의 다양한 sql 문장 실행 가능
		Statement stmt = null;
		try{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int result = stmt.executeUpdate("insert into dept values("+dept.getDeptno()+",'"+dept.getDname()+"' ,'"+dept.getLoc()+"')");
			if(result == 1){
				System.out.println("success");
			}
		}finally {
			DBUtil.close(con, stmt);
		}
	}
	
	// 값만 가변적인 동일한 sql 문장만 실행하고자 할대 권장
	// 왜? 개발도 편리하고 DB자체적으로 시행 속도가 빠른 API
	// 단점 : 하나의 문장 실행 객체로 다양한 sql 실행 불가
	static void insertDept2(DeptDTO dept){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into dept values(?, ?, ?)");
			//sql문장에 단순 값 반영
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			int result = pstmt.executeUpdate();
			if(result == 1){
				System.out.println("success");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pstmt);
		}
	}

	// 개발한 메소드들 확인 단계
	/*
	 * select All -> insert -> select
	 * select All -> insert -> select All
	 */
	public static void main(String[] args) {
		System.out.println("==========전체 검색===========");
		try {
			ArrayList<DeptDTO> all = selectAll();
			if(all.size() != 0){
				for(DeptDTO v : all){
					System.out.println(v.toString());
				}
			}else{
				System.out.println("검색 데이터가 미 존재합니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("모든 데이터 검색시 오류");
		}
		
		System.out.println("==========저장===========");
		try {
			insertDept(new DeptDTO(55, "인사부", "서울역"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("저장시 오류 : 데이터 무결성 발생");
		}
		
		System.out.println("==========부서 검색===========");
		try {
			DeptDTO data = selectDept(55);
			if(data != null){
				System.out.println(data.toString());
			}else{
				System.out.println("해당 부서 정보는 존재하지 않습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("부서 번호로 검색시 오류");
		}
		
		System.out.println("==========전체 검색===========");
		try {
			ArrayList<DeptDTO> all = selectAll();
			if(all.size() != 0){
				for(DeptDTO v : all){
					System.out.println(v.toString());
				}
			}else{
				System.out.println("검색 데이터가 미 존재합니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("모든 데이터 검색시 오류");
		}
	}
}
