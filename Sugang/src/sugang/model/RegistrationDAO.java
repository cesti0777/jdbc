package sugang.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import sugang.model.dto.RegistrationDTO;
import sugang.model.util.DBUtil;
import sugang.service.SugangMenuController;

public class RegistrationDAO {
	static Properties sql = DBUtil.getProperties();

	// 학번으로 해당 학생의 수강신청 정보 반환
	public static ArrayList<RegistrationDTO> getRegistration(String stuCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RegistrationDTO> all = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getRegistration"));
			pstmt.setString(1, stuCode);
			rset = pstmt.executeQuery();
			all = new ArrayList<RegistrationDTO>();
			while (rset.next()) {
				all.add(new RegistrationDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}

	public static boolean updateCurrentCount(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updateCurrentCount"));
			pstmt.setString(1, lecCode);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean addRegistration(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String stuCode = SugangMenuController.session.getCode();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addRegistration"));
			pstmt.setString(1, stuCode);
			pstmt.setString(2, lecCode);
			pstmt.setString(3, stuCode);
			pstmt.setString(4, lecCode);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean addNewDayRegistration(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String stuCode = SugangMenuController.session.getCode();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addNewDayRegistration"));
			pstmt.setString(1, stuCode);
			pstmt.setString(2, lecCode);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean verifyCount(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("verifyCount"));
			pstmt.setString(1, lecCode);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean verifyExist(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String stuCode = SugangMenuController.session.getCode();
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("verifyExist"));
			pstmt.setString(1, stuCode);
			pstmt.setString(2, lecCode);
			rset = pstmt.executeQuery();
			if (!rset.next()) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean verifyNewDay(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String stuCode = SugangMenuController.session.getCode();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("verifyNewDay"));
			pstmt.setString(1, stuCode);
			pstmt.setString(2, lecCode);
			rset = pstmt.executeQuery();
			if (!rset.next()) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static boolean deleteRegistration(String lecCode) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int result1 = stmt.executeUpdate("delete from registration where lcode = '" + lecCode + "' and sCode = '"
					+ SugangMenuController.session.getCode() + "'");
			if (result1 == 1) {
				int result2 = stmt
						.executeUpdate("update lecture set lcurrent = lcurrent - 1 where lcode = '" + lecCode + "'");
				if (result2 == 1) {
					return true;
				}
			}
		} finally {
			DBUtil.close(con, stmt);
		}
		return false;
	}

}
