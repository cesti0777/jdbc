/*
 *  �н�����
 *  1. �� �޼ҵ庰�� ��� ����
 *  	select(��ü, �μ���ȣ�� �ش� �μ���)
 *  	insert / update / delete
 *  
 *  2. ȿ������ �ڵ� ����
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
	
	//�μ� ��ȣ�� �ش� �μ��� ��� ���� �˻�
	//�˻��ؼ� �ܼ� ���? ��ȯ�ؼ� ��û������ ������ ������ ...
	/*
	 * ����� �� 
	 * 1. SQLException
	 * 2. ���� ����
	 * 		1. ������ ���� = DeptDTO
	 * 		2. ������ �� ���� - null
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
	
	//��� �μ� ���� �˻�
	/*
	 * 1. SQLException
	 * 		- DB���� ����, sql���� ����
	 * 
	 * 2. table���� �� ������ ����
	 * 		- ArrayList�� view�� ���
	 * 
	 * 3. table���� ������ ��
	 * 		- ArrayList ��ü�� ����, ������ size() 0
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
	
	/*
	 * insert �õ��� �߻� ������ ����� ��
	 * 1. ���������� insert
	 * 2. �����ϴ� deptno�� �ߺ�, ���Ἲ ���� �߻�
	 * 		- SQLException �߻�
	 */
	static void insertDept(DeptDTO dept) throws SQLException{
		Connection con = null;
		// �ϳ��� �� ��ü�� �ټ��� �پ��� sql ���� ���� ����
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
	
	// ���� �������� ������ sql ���常 �����ϰ��� �Ҵ� ����
	// ��? ���ߵ� ���ϰ� DB��ü������ ���� �ӵ��� ���� API
	// ���� : �ϳ��� ���� ���� ��ü�� �پ��� sql ���� �Ұ�
	static void insertDept2(DeptDTO dept){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into dept values(?, ?, ?)");
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
		try {
			insertDept(new DeptDTO(55, "�λ��", "���￪"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����� ���� : ������ ���Ἲ �߻�");
		}
		
		System.out.println("==========�μ� �˻�===========");
		try {
			DeptDTO data = selectDept(55);
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
