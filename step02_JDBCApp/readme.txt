학습 내용

[1] 문제 1

1. 필요한 sql문장
	- 가정 : dept는 4개의 컬럼, emp에는 3개의 컬럼으로 구성되었다 가정
	1. select * from dept;
	2. select empno, ename, e.deptno, dname, loc
		from emp e, dept d
		where e.deptno = d.deptno;
	3. select empno, ename, deptno from emp;
	
2. DTO(Data Transfer Object) = VO(Value Object) = java bean
	1. DTO 클래스는 몇개?
		방법1 : table당 개별 class로 개발, 2개
			EmpDTO.java(empno, ename, deptno)
			DeptDTO.java(deptno, dname, loc)
			
		방법2 : select문장들 분석후에 (권장)
			EmpDTO.java(empno, ename, deptno)
			DeptDTO.java(deptno, dname, loc)
			EmpDeptDTO.java(empno, ename, deptno, dname, loc)
			
	2. DTO 클래스의 멤버 변수들 구성은?
	
[2] 문제 2 : 프렌차이즈라 가정

1. 프렌차이즈 domain(DTO=VO=bean) 멤버로 어떻게 구성 할까요?
	1. 데이터 : 회사명, 오픈일, 본사 위치....
					가맹점들의 정보(점주, 전화, 위치, 직원수.....)
	2. class 프렌차이즈{
			회사명,
			오픈일,
			본사위치,
			list(가맹점DTO(점주, 전번, 위치, 직원수...))		
		}		
	

