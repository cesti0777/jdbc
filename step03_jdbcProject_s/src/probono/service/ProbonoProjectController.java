package probono.service;

import java.sql.SQLException;
import java.util.ArrayList;

import probono.model.ActivistDAO;
import probono.model.ProbonoDAO;
import probono.model.ProbonoProjectDAO;
import probono.model.ProbonoService;
import probono.model.dto.ActivistDTO;
import probono.model.dto.ProbonoDTO;
import probono.model.dto.ProbonoProjectDTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ProbonoProjectController {
	
	//모든 프로젝트 검색 로직
	/* 요청시 발생 가능한 경우의 수
	 * 1. 데이터가 있다
	 * 2. 진행 프로젝트가 없다, 데이터가 없다
	 * 3. 서버 문제 발생(db접속 문제 또는 개발자의 실수로 sql문장 오류...) */
	public static void getAllProbonoProjects(){ // ArrayList 반환 하지 않고 void로 처리 
																			  // 이 메소드 안에서 view단 까지 처리해버림.
		try{			
			RunningEndView.projectListView(ProbonoService.getAllProbonoUsers());	
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
	}
	
	//새로운 프로젝트 저장 로직
	public static void addProbonoProject(ProbonoProjectDTO probonoProject) {
		try{
			ProbonoService.addProbonoUser(probonoProject);
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 저장시 에러 발생");
		}
	}
	
	//모든 프로젝트 검색 로직
	public static void getAllActivists(){
		try{
			RunningEndView.projectListView(ProbonoService.getAllActivists());
		}catch(SQLException s){
			s.printStackTrace();
			RunningEndView.showError("모든 재능 기부자 검색시 에러 발생");
		}
	}
	
	//프로보노 아이디로 프로보노 목적 수정
	public static void updateProbono(String probonoId, String probonoPurpose){
		try{
			ProbonoService.updateProbono(probonoId, probonoPurpose);
		}catch(Exception s){
			s.printStackTrace();
			RunningEndView.showError("프로보노 id로 프로보노 목적 변경 오류");
		}
	}
	
	//프로보노 정보 검색
	public static void getProbono(String probonoId) {
		try {
			RunningEndView.allView(ProbonoService.getProbono(probonoId));
		} catch (Exception e) {
			e.printStackTrace();
			RunningEndView.showError("프로보노 id로 해당 프로보노 검색 오류 ");
		}
	}
}
