�н� ����

[1] ���� 1

1. �ʿ��� sql����
	- ���� : dept�� 4���� �÷�, emp���� 3���� �÷����� �����Ǿ��� ����
	1. select * from dept;
	2. select empno, ename, e.deptno, dname, loc
		from emp e, dept d
		where e.deptno = d.deptno;
	3. select empno, ename, deptno from emp;
	
2. DTO(Data Transfer Object) = VO(Value Object) = java bean
	1. DTO Ŭ������ �?
		���1 : table�� ���� class�� ����, 2��
			EmpDTO.java(empno, ename, deptno)
			DeptDTO.java(deptno, dname, loc)
			
		���2 : select����� �м��Ŀ� (����)
			EmpDTO.java(empno, ename, deptno)
			DeptDTO.java(deptno, dname, loc)
			EmpDeptDTO.java(empno, ename, deptno, dname, loc)
			
	2. DTO Ŭ������ ��� ������ ������?
	
[2] ���� 2 : ����������� ����

1. ���������� domain(DTO=VO=bean) ����� ��� ���� �ұ��?
	1. ������ : ȸ���, ������, ���� ��ġ....
					���������� ����(����, ��ȭ, ��ġ, ������.....)
	2. class ����������{
			ȸ���,
			������,
			������ġ,
			list(������DTO(����, ����, ��ġ, ������...))		
		}		
	

