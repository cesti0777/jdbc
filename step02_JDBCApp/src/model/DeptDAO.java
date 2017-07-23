/*
 *  �н�����
 *  1. �� �޼ҵ庰�� ��� ����
 *  	select(��ü, �μ���ȣ�� �ش� �μ���)
 *  	insert / update / delete
 *  
 *  2. ȿ������ �ڵ� ����
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
			
			all = new ArrayList<DeptDTO>();//���⼭ �����ϴ� ������ ���� con, stmt, rset���� ����� ���� �������� ���� ���¶�
																//������ ������ ���� ����.
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
			//sql���忡 �ܼ� �� �ݿ�
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

	// ������ �޼ҵ�� Ȯ�� �ܰ�
	/*
	 * select All -> insert -> select
	 * select All -> insert -> select All
	 */
	public static void main(String[] args) {
		System.out.println("==========��ü �˻�===========");
		try {
			ArrayList<DeptDTO> all = selectAll();
			if(all.size() != 0){
				for(DeptDTO v : all){
					System.out.println(v.toString());
				}
			}else{
				System.out.println("�˻� �����Ͱ� �� �����մϴ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��� ������ �˻��� ����");
		}
		
		System.out.println("==========����===========");
//		try {
//			insertDept2(new DeptDTO(57, "������", "ȫ�뿪"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("����� ���� : ������ ���Ἲ �߻�");
//		}
		System.out.println("���� ����");
		
		System.out.println("==========�μ� �˻�===========");
		try {
			DeptDTO data = selectDept(57);
			if(data != null){
				System.out.println(data.toString());
			}else{
				System.out.println("�ش� �μ� ������ �������� �ʽ��ϴ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�μ� ��ȣ�� �˻��� ����");
		}
		
		System.out.println("==========��ü �˻�===========");
		try {
			ArrayList<DeptDTO> all = selectAll();
			if(all.size() != 0){
				for(DeptDTO v : all){
					System.out.println(v.toString());
				}
			}else{
				System.out.println("�˻� �����Ͱ� �� �����մϴ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��� ������ �˻��� ����");
		}
	}
}
