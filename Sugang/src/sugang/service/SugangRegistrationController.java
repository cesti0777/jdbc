package sugang.service;

import java.sql.SQLException;
import java.util.ArrayList;

import sugang.model.LectureDAO;
import sugang.model.RegistrationDAO;
import sugang.model.dto.LectureDTO;
import sugang.model.dto.RegistrationDTO;
import sugang.view.RunningEndView;

public class SugangRegistrationController {

	public static boolean getAllLectures() {
		ArrayList<LectureDTO> allLectureList = null;
		boolean isData = false;
		try {
			allLectureList = LectureDAO.getAllLectures();
			if (allLectureList.size() != 0) {
				RunningEndView.lectureListView(allLectureList);
				isData = true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("강의 정보 검색 시 에러 발생");
		}
		return isData;
	};

	public static boolean getLectureByMname(String majName) {
		ArrayList<LectureDTO> lecturLlist = null;
		boolean isData = false;
		try {
			lecturLlist = LectureDAO.getLectureByMname(majName);
			if (lecturLlist.size() != 0) {
				RunningEndView.lectureListView(lecturLlist);
				isData = true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("강의 정보 검색 시 에러 발생");
		}
		return isData;
	};

	public static boolean getLectureByLname(String lecName) {
		ArrayList<LectureDTO> lecturLlist = null;
		boolean isData = false;
		try {
			lecturLlist = LectureDAO.getLectureByLname(lecName);
			if (lecturLlist.size() != 0) {
				RunningEndView.lectureListView(lecturLlist);
				isData = true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("강의 정보 검색 시 에러 발생");
		}
		return isData;
	};

	public static boolean getLectureByLcode(String lecCode) {
		ArrayList<LectureDTO> lecturLlist = null;
		boolean isData = false;
		try {
			lecturLlist = LectureDAO.getLectureByLcode(lecCode);
			if (lecturLlist.size() != 0) {
				RunningEndView.lectureListView(lecturLlist);
				isData = true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("강의 정보 검색 시 에러 발생");
		}
		return isData;
	};

	public static boolean getRegistration(String stuCode) {
		ArrayList<RegistrationDTO> allRegistrationList = null;
		boolean isData = false;
		try {
			allRegistrationList = RegistrationDAO.getRegistration(stuCode);
			if (allRegistrationList.size() != 0) {
				RunningEndView.registrationListView(allRegistrationList);
				isData = true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("강의 정보 검색 시 에러 발생");
		}
		return isData;
	};

	public static void addRegistration(String lecCode) {
		try {
			if (RegistrationDAO.verifyExist(lecCode)) {
				if (RegistrationDAO.verifyCount(lecCode)) {
					if (RegistrationDAO.verifyNewDay(lecCode)) {
						if (RegistrationDAO.addNewDayRegistration(lecCode)) {
							if (RegistrationDAO.updateCurrentCount(lecCode)) {
								RunningEndView.showSuccess("수강신청 성공!");
							}
						} else {
							RunningEndView.showError("기존에 신청한 과목과 시간이 중복되어 신청할 수 없습니다.");
						}
					} else {
						if (RegistrationDAO.addRegistration(lecCode)) {
							if (RegistrationDAO.updateCurrentCount(lecCode)) {
								RunningEndView.showSuccess("수강신청 성공!");
							}
						} else {
							RunningEndView.showError("기존에 신청한 과목과 시간이 중복되어 신청할 수 없습니다.");
						}
					}
				} else {
					RunningEndView.showError("수용 가능 인원이 초과된 과목입니다.");
				}
			} else {
				RunningEndView.showError("이미 신청된 과목입니다.");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("수강신청 SQL에러 발생");
		}
	};

	public static void deleteRegistration(String lecCode) {
		try {
			if (RegistrationDAO.deleteRegistration(lecCode)) {
				RunningEndView.showSuccess("수강취소 성공");
			} else {
				RunningEndView.showError("해당 코드의 강의내역이 없습니다");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("수강신청 에러 발생");
		}
	};
}
