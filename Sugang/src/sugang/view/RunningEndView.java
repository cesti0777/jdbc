package sugang.view;

import java.util.ArrayList;

import sugang.model.dto.LectureDTO;
import sugang.model.dto.RegistrationDTO;
import sugang.model.dto.StudentDTO;

public class RunningEndView {
	// 모든 강의 출력
	public static void lectureListView(ArrayList<LectureDTO> allLecturelist) {
		for (int index = 0; index < allLecturelist.size(); index++) {
			System.out.println(allLecturelist.get(index));
		}
	}

	// 모든 강의 출력
	public static void registrationListView(ArrayList<RegistrationDTO> allRegistrationlist) {
		for (int index = 0; index < allRegistrationlist.size(); index++) {
			System.out.println(allRegistrationlist.get(index));
		}
	}

	// 로그인 정보 출력
	public static void loginInfoView(StudentDTO student) {
		System.out.println(student.getName() + "(" + student.getStuId() + ")님 환영합니다");
	}

	// 예외 상황 출력
	public static void showError(String message) {
		System.out.println(message);
	}

	public static void showSuccess(String message) {
		System.out.println(message);
	}

	public static void showMenuInfomation(String flag) {
		if (flag.equals("MainMenu")) {
			System.out.println("");
			System.out.println("[메인메뉴]");
			System.out.println("1. 강의조회/신청");
			System.out.println("2. 수강내역조회/편집");
			System.out.println("3. 종료");
			System.out.print("번호를 선택하세요 : ");
		} else if (flag.equals("LectureMenu")) {
			System.out.println("");
			System.out.println("[강의조회/신청]");
			System.out.println("1. 전체강의조회");
			System.out.println("2. 학과별조회");
			System.out.println("3. 강의명조회");
			System.out.println("4. 강의코드조회");
			System.out.println("5. 메인으로");
			System.out.print("번호를 선택하세요 : ");
		} else if (flag.equals("LoginMenu")) {
			System.out.println("=====수강신청시스템=====");
			System.out.println("[로그인]");
			System.out.print("아이디: ");
		} else if (flag.equals("ShowRegistrationList")) {
			System.out.println("");
			System.out.println("[수강내역조회/편집]");
		} else if (flag.equals("ShowAllLectureList")) {
			System.out.println("");
			System.out.println("[전체강의조회]");
		} else if (flag.equals("ShowByMajorList")) {
			System.out.println("");
			System.out.println("[학과별조회]");
			System.out.print("학과명을 입력하세요 : ");
		} else if (flag.equals("ShowByLectureNameList")) {
			System.out.println("");
			System.out.println("[강의명조회]");
			System.out.print("강의명을 입력하세요 : ");
		} else if (flag.equals("ShowByLectureCodeList")) {
			System.out.println("");
			System.out.println("[강의코드조회]");
			System.out.print("강의코드를 입력하세요 : ");
		} else if (flag.equals("DeleteRegistrationMenu")) {
			System.out.println("");
			System.out.println("[수강취소]");
			System.out.print("취소할 강의의 코드를 입력하세요 : ");
		} else if (flag.equals("NewRegistration")) {
			System.out.println("");
			System.out.println("[수강신청하기]");
			System.out.print("수강신청할 강의의 코드를 입력하세요 : ");
		} else if (flag.equals("Exit")) {
			System.out.println("");
			System.out.println("[시스템을 종료합니다]");
		} else if (flag.equals("DeleteRegistration")) {
			System.out.println("1. 수강취소하기");
			System.out.println("2. 메인으로");
			System.out.print("번호를 선택하세요 : ");
		} else if (flag.equals("Registration")) {
			System.out.println("1. 수강신청하기");
			System.out.println("2. 강의조회화면으로");
			System.out.print("번호를 선택하세요 : ");
		}
	}
}
