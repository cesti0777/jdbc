package sugang.model.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	static Properties db = new Properties();
	static Properties sql = new Properties();

	static {
		try {
			db.load(new FileReader("db.properties"));
			sql.load(new FileReader("sql.properties"));
			Class.forName(db.getProperty("jdbc.driver"));
		} catch (Exception c) {
			c.printStackTrace();
		}
	}

	public static Properties getProperties() {
		return sql;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(db.getProperty("jdbc.url"), db.getProperty("jdbc.id"),
				db.getProperty("jdbc.pw"));
	}

	// DML¿ë
	public static void close(Connection con, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// SELECT¿ë
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			close(con, stmt);
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
