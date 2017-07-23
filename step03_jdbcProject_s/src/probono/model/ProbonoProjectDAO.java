/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */
package probono.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import probono.model.dto.ProbonoProjectDTO;
import probono.model.util.DBUtil;

public class ProbonoProjectDAO {
	static Properties sql = DBUtil.getProperties();
	
	//���κ��� ������Ʈ ����
	public static boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addProbonoProject"));
			pstmt.setString(1, probonoProject.getProbonoProjectName());
			pstmt.setString(2, probonoProject.getProbonoId());
			pstmt.setString(3, probonoProject.getActivistId());
			pstmt.setString(4, probonoProject.getReceiveId());
			pstmt.setString(5, probonoProject.getProjectContent());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
			
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	//����
	// ���κ��� ������Ʈ ID��  ��ɱ���� ����
	public static boolean updateProbonoProjectActivist(int projectId, String activistId) throws SQLException{		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(sql.getProperty("updateProbonoProjectActivist"));
			pstmt.setString(1, activistId);
			pstmt.setInt(2, projectId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	//����
	//���κ��� ������Ʈ id�� ������ ���� ����
	public static boolean updateProbonoProjectReceive(int probonoProjectId, String  receiveId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(sql.getProperty("updateProbonoProjectReceive"));
			pstmt.setString(1, receiveId);
			pstmt.setInt(2, probonoProjectId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	//���� 
	//���κ��� ������Ʈ id�� ���κ��� ������Ʈ ����
	public static boolean deleteProbonoProject(int probonoProjectId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("deleteProbonoProject"));
			pstmt.setInt(1, probonoProjectId);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	//���κ��� ������Ʈ id�� �ش� ���κ���������Ʈ �˻�
	public static ProbonoProjectDTO getProbonoProject(int probonoProjectId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProbonoProjectDTO probonoUser = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getProbonoProject"));
			pstmt.setInt(1, probonoProjectId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				probonoUser = new ProbonoProjectDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return probonoUser;
	}
	
	//��� ���κ��� ������Ʈ �˻� 
	public static ArrayList<ProbonoProjectDTO> getAllProbonoProjects() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProbonoProjectDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getAllProbonoProjects"));
			rset = pstmt.executeQuery();
			
			list = new ArrayList<ProbonoProjectDTO>();
			while(rset.next()){
				list.add( new ProbonoProjectDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}