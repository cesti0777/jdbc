package sugang.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sugang.model.StudentDAO;
import sugang.model.dto.StudentDTO;
import sugang.view.RunningEndView;

public class SugangMenuController {
	static String[] logInfo = new String[2];
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int menu = 0;
	static int selectedMenu = 0;
	public static StudentDTO session = null;

	public static void initStartMenu() {

		try {
			while (true) {
				switch (menu) {
				case 0: // 로그인
					loginMenu();
					continue;
				case 1: // 메인메뉴
					mainMenu();
					continue;
				case 11: // 강의조회/신청
					showLectureList();
					continue;
				case 12: // 수강내역조회/편집
					showRegistrationList();
					continue;
				case 13: // 종료
					RunningEndView.showMenuInfomation("Exit");
					break;
				case 111:// 전체강의조회
					showAllLectureList();
					continue;
				case 112:// 학과별조회
					showByMajorList();
					continue;
				case 113:// 강의명조회
					showByLectureNameList();
					continue;
				case 114:// 강의코드조회
					showByLectureCodeList();
					continue;
				case 121:// 수강취소
					deleteRegistration();
					continue;
				case 1111:// 수강신청하기
					newRegistration();
					continue;
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void loginMenu() {
		RunningEndView.showMenuInfomation("LoginMenu");
		try {
			logInfo[0] = in.readLine();
			System.out.print("비밀번호: ");
			logInfo[1] = in.readLine();
			session = StudentDAO.getLoginInfo(logInfo);
			if (session != null) {
				RunningEndView.loginInfoView(session);
				menu = 1;
			} else {
				RunningEndView.showError("아이디 혹은 비밀번호 입력값이 잘못되었습니다.");
				loginMenu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		RunningEndView.showMenuInfomation("MainMenu");
		try {
			selectedMenu = Integer.parseInt(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (selectedMenu == 1) {// 강의조회/신청
			menu = 11;
		} else if (selectedMenu == 2) {// 수강내역조회/편집
			menu = 12;
		} else if (selectedMenu == 3) {// 종료
			menu = 13;
		} else {
			RunningEndView.showError("올바른 번호를 입력하세요");
		}
	}

	public static void showLectureList() {
		RunningEndView.showMenuInfomation("LectureMenu");
		try {
			selectedMenu = Integer.parseInt(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (selectedMenu == 1) {// 전체강의조회
			menu = 111;
		} else if (selectedMenu == 2) {// 학과별조회
			menu = 112;
		} else if (selectedMenu == 3) {// 강의명조회
			menu = 113;
		} else if (selectedMenu == 4) {// 강의코드조회
			menu = 114;
		} else if (selectedMenu == 5) {// 메인으로
			menu = 1;
		} else {
			RunningEndView.showError("올바른 번호를 입력하세요");
		}
	}

	public static void showRegistrationList() {
		RunningEndView.showMenuInfomation("ShowRegistrationList");
		try {
			if (SugangRegistrationController.getRegistration(session.getCode())) {
				RunningEndView.showMenuInfomation("DeleteRegistration");
				selectedMenu = Integer.parseInt(in.readLine());
				if (selectedMenu == 1) {// 수강취소하기
					menu = 121;
				} else if (selectedMenu == 2) {// 메인으로
					menu = 1;
				} else {
					RunningEndView.showError("올바른 번호를 입력하세요");
				}
			} else {
				RunningEndView.showError("수강 신청 내역이 존재하지 않습니다");
				menu = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAllLectureList() {
		RunningEndView.showMenuInfomation("ShowAllLectureList");
		if (SugangRegistrationController.getAllLectures()) {
			RunningEndView.showMenuInfomation("Registration");
			try {
				selectedMenu = Integer.parseInt(in.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (selectedMenu == 1) {// 수강신청하기
				menu = 1111;
			} else if (selectedMenu == 2) {// 강의조회화면으로
				menu = 11;
			} else {
				RunningEndView.showError("올바른 번호를 입력하세요");
			}
		} else {
			RunningEndView.showError("검색된 강의가 없습니다");
			menu = 11;
		}
	}

	public static void showByMajorList() {
		RunningEndView.showMenuInfomation("ShowByMajorList");
		String mName = null;
		try {
			mName = in.readLine();
			if (SugangRegistrationController.getLectureByMname(mName)) {
				RunningEndView.showMenuInfomation("Registration");
				selectedMenu = Integer.parseInt(in.readLine());
				if (selectedMenu == 1) {// 수강신청하기
					menu = 1111;
				} else if (selectedMenu == 2) {// 강의조회화면으로
					menu = 11;
				} else {
					RunningEndView.showError("올바른 번호를 입력하세요");
				}
			} else {
				RunningEndView.showError("검색된 강의가 없습니다");
				menu = 11;
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showByLectureNameList() {
		RunningEndView.showMenuInfomation("ShowByLectureNameList");
		String lName = null;
		try {
			lName = in.readLine();
			if (SugangRegistrationController.getLectureByLname(lName)) {
				RunningEndView.showMenuInfomation("Registration");
				selectedMenu = Integer.parseInt(in.readLine());
				if (selectedMenu == 1) {// 수강신청하기
					menu = 1111;
				} else if (selectedMenu == 2) {// 강의조회화면으로
					menu = 11;
				} else {
					RunningEndView.showError("올바른 번호를 입력하세요");
				}
			} else {
				RunningEndView.showError("검색된 강의가 없습니다");
				menu = 11;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showByLectureCodeList() {
		RunningEndView.showMenuInfomation("ShowByLectureCodeList");
		String lecCode = null;
		try {
			lecCode = in.readLine();
			if (SugangRegistrationController.getLectureByLcode(lecCode)) {
				RunningEndView.showMenuInfomation("Registration");
				selectedMenu = Integer.parseInt(in.readLine());
				if (selectedMenu == 1) {// 수강신청하기
					menu = 1111;
				} else if (selectedMenu == 2) {// 강의조회화면으로
					menu = 11;
				} else {
					RunningEndView.showError("올바른 번호를 입력하세요");
				}
			} else {
				RunningEndView.showError("검색된 강의가 없습니다");
				menu = 11;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteRegistration() {
		RunningEndView.showMenuInfomation("DeleteRegistrationMenu");
		String lecCode = null;
		try {
			lecCode = in.readLine();
			SugangRegistrationController.deleteRegistration(lecCode);
			menu = 12;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newRegistration() {
		RunningEndView.showMenuInfomation("NewRegistration");
		String lecCode = null;
		try {
			lecCode = in.readLine();
			SugangRegistrationController.addRegistration(lecCode);
			menu = 11;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
