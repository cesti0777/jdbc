/*
 *  학습내용
 *  1. 각 메소드별로 기능 구현
 *  	select(전체, 부서번호로 해당 부서만)
 *  	insert / update / delete
 *  
 *  2. 효율적인 코드 구현
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import model.domain.DeptDTO;
import util.DBUtil;

public class DeptDAO {
	
	static Properties sql = DBUtil.getProperties();
	
	static DeptDTO selectDept(int deptno) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("selectDept"));
			pstmt.setInt(1, deptno);
			rset = pstmt.executeQuery();
			if(rset.next()){
				return new DeptDTO(rset.getInt(1), rset.getString(2), rset.getString(3));
			}
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
	static ArrayList<DeptDTO> selectAll() throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		ArrayList<DeptDTO> all = null;
		try{
			con = DBUtil.getConnection();
			stmt = con.prepareStatement(sql.getProperty("selectAll"));
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

	static void insertDept2(DeptDTO dept) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("insertDept2"));
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
//		try {
//			insertDept2(new DeptDTO(57, "행정부", "홍대역"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("저장시 오류 : 데이터 무결성 발생");
//		}
		System.out.println("저장 안함");
		
		System.out.println("==========부서 검색===========");
		try {
			DeptDTO data = selectDept(57);
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
