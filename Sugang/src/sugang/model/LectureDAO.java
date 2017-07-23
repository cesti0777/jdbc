package sugang.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import sugang.model.dto.LectureDTO;
import sugang.model.util.DBUtil;

public class LectureDAO {
	static Properties sql = DBUtil.getProperties();

	public static ArrayList<LectureDTO> getAllLectures() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LectureDTO> all = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getAllLectures"));
			rset = pstmt.executeQuery();
			all = new ArrayList<LectureDTO>();
			while (rset.next()) {
				all.add(new LectureDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}

	public static ArrayList<LectureDTO> getLectureByMname(String majName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LectureDTO> all = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getLectureByMname"));
			pstmt.setString(1, "%" + majName + "%");
			rset = pstmt.executeQuery();
			all = new ArrayList<LectureDTO>();
			while (rset.next()) {
				all.add(new LectureDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}

	public static ArrayList<LectureDTO> getLectureByLname(String lecName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LectureDTO> all = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getLectureByLname"));
			pstmt.setString(1, "%" + lecName + "%");
			rset = pstmt.executeQuery();
			all = new ArrayList<LectureDTO>();
			while (rset.next()) {
				all.add(new LectureDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}

	public static ArrayList<LectureDTO> getLectureByLcode(String lecCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LectureDTO> all = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getLectureByLcode"));
			pstmt.setString(1, "%" + lecCode + "%");
			rset = pstmt.executeQuery();
			all = new ArrayList<LectureDTO>();
			while (rset.next()) {
				all.add(new LectureDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}

}
