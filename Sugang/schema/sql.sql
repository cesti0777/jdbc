select * from major;
select * from student;
select * from professor;
select * from lecture;
select * from registration;

select * from lecture where lcode = '1111'

select * from lecturelist;

select * from lecturelist where mname='소프트웨어학과'

select * from lecturelist where mname='?'

desc lecturelist;

select * from lecturelist where lcode = '1111'

/*
deleteRegistration에 lecCode 가 전달됨 (취소할 강의코드)
1. lecCode가 내 수강신청 내역에 존재하는지 확인
	내 stuCode이면서 lecCode가 같은게 있는지 확인
2. lecCode인 강의의 current-1
*/

delete from registration where lcode = '1111' and sCode = '201314134'


insert into registration values(registration_seq_rnum.nextval, '201314135', '2222');
update lecture set lcurrent = lcurrent + 1 where lcode = '2222'

insert into registration values(registration_seq_rnum.nextval, '201314134', '1111');
update lecture set lcurrent = lcurrent + 1 where lcode = '1111'

insert into registration values(registration_seq_rnum.nextval, '201212111', '1133');
update lecture set lcurrent = lcurrent + 1 where lcode = '1133'

select * from registration;
select * from lecture;

[강의코드:1111 | 강의명:소프트웨어공학 | 학과:소프트웨어학과 | 담당교수 : 남원홍 | 월 13:00~15:00 | 1/30]

select * from registrationlist;


select * 
from lecturelist 
where lcode = '2222' and '2222' not in(select r.lcode 
														      from registration r, registrationlist rl 
														      where r.SCODE = '201314135' and r.LCODE = rl.LCODE)

														      
select * from lecture;			      
