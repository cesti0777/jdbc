/* ???
 * 1. JDBC URL - oracle.jdbc.driver.OracleDriver
 */
package probono.model.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	static Properties dbInfo = new Properties();
	static Properties sql = new Properties();

	
	static{ // ����̹� �ε��� �� �ѹ���.
		try {
			dbInfo.load(new FileReader("dbinfo.properties"));
			sql.load(new FileReader("sql.properties"));
			Class.forName(dbInfo.getProperty("jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProperties(){
		return sql;
	}
	
	public static Connection getConnection() throws SQLException{ // ���� ��Ȳ�� client���� ������ �ż��� ó�� ����
		return DriverManager.getConnection(dbInfo.getProperty("jdbc.url"),
																	  dbInfo.getProperty("jdbc.id"),
																	  dbInfo.getProperty("jdbc.pw"));
		//property�� �Ἥ ���̻� �ڹ� �ڵ� ������ ���ġ ���� �ʵ��� �ϴ°� ����.
		
	}
	
	// query = select�� �ڿ���ȯ
	public static void close(Connection con, Statement stmt, ResultSet rset){ // ���� ������ �ʾƵ� ��. ������ �˾Ƽ� ó��.
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
		}
	}
	
	// dml�� �ڿ���ȯ
	public static void close(Connection con, Statement stmt){ // �����ս� ����ؼ� dml�� ���� ����.
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
	
	public static void main(String[] args){
		try{
			System.out.println(getConnection());
		} catch( SQLException e){
			e.printStackTrace();
		}
	}
}
