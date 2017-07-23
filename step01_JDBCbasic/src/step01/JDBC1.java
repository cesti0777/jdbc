/*
 * 1. JDBC?
 * 		- Java DataBase Connectivity
 * 		- DB ������ �����ϰ� DB���� ǥ�� API
 * 
 *	2. ������ ���� ȯ�� ����
 *		- db�� driver�� ����
 *		- �����ϴ� �� ���� : DB�� ���ϰ� ��� �����ϰ� �����簡 ���� 
 *
 * 3. ���� �ܰ�
 * 		1�ܰ� : driver�� �޸𸮿� �ε�, �ѹ��� �ε��Ͽ� ����
 * 		2�ܰ� : ���� ��ü ����(Connection), �����ϸ� �ȵ�
 * 		3�ܰ� : sql����� ����(Statement or PreparedStatement)
 * 					ResultSet executeQuery(String select){}
 * 		4�ܰ� : ���� ����� Ȱ��
 * 					ResultSet�� �ֿ� API
 * 						1. boolean next() : �˻��� ���� row�� �����ϳ� Ȯ��
 * 						2. Ÿ�� getXxx("�÷���" or �˻��� �÷��� ��ġ��ȣ)
 * 							: �ش� �÷��� �����Ͱ� ��ȯ
 * 		5�ܰ� : db ���� ������ ���� �ڿ� ��ȯ
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
			//System.err.println(con); //oracle.jdbc.driver.T4CConnection@4d76f3f8 �ּ� ������ ���� ���� 
												       //null������ ���� ����			
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
			try { // �ڿ� ��ȯ ���� �ſ� �߿�, ���� �ֱ� ���� �� ���� ��ȯ
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















