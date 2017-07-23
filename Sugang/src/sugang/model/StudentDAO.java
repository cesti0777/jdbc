package sugang.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import sugang.model.dto.StudentDTO;
import sugang.model.util.DBUtil;

public class StudentDAO {
	static Properties sql = DBUtil.getProperties();

	public static StudentDTO getLoginInfo(String[] logInfo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StudentDTO sessionInfo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getLoginInfo"));
			pstmt.setString(1, logInfo[0]);
			pstmt.setString(2, logInfo[1]);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				sessionInfo = new StudentDTO(rset.getString(1), rset.getString(2), rset.getString(3),
						rset.getString(4));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return sessionInfo;
	}
}
