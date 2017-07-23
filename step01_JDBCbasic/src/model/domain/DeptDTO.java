/*
 * 1. 데이터용 전담 클래스
 * 2. 데이터로 객체 생성후에 다양한 메소드 반환, parameter로 여기저기 전송
 * 3. 용어
 * 		- Value Object Pattern
 * 		- Data Transfer Object Pattern
 * 		- Java Bean
 * 4. 클래스명 권장
 * 		- Dept/DeptVO/DeptDTO/DeptBean
 */

package model.domain;

public class DeptDTO {
	private int deptno;
	private String dname;
	private String loc;

	public DeptDTO() {}
	public DeptDTO(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptDTO [deptno=");
		builder.append(deptno);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", loc=");
		builder.append(loc);
		builder.append("]");
		return builder.toString();
	}

}
