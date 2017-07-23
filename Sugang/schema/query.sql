-- 실행 : alt+s
-- major table

drop table major cascade constraint;

create table major(
	mcode varchar2(30) not null,
	mname varchar2(30) not null,
	constraint major_mnum_pk primary key(mcode)
);

-- Test data 생성
insert into major values('soft', '소프트웨어학과');
insert into major values('com', '컴퓨터공학과');
insert into major values('busi', '경영학과');

commit;

select * from major;

-- student table

drop table student cascade constraint;

create table student(
	scode varchar2(30) not null,
	sname varchar2(30) not null,
	mcode varchar2(30) not null,
	sid varchar2(30) not null,
	spassword varchar2(30) not null,
	constraint student_scode_pk primary key(scode),
	constraint student_mcode_fk foreign key(mcode) references major(mcode)
);

-- Test data 생성
insert into student values('201314135', '백경진', 'soft', 'gpfqk', 'asdf');
insert into student values('201314134', '박지빈', 'soft', 'cesti0777', 'asdf');
insert into student values('201212111', '김형준', 'com', 'king7854', 'dsaf');

commit;

select * from student;

-- professor table

drop table professor cascade constraint;

create table professor (
	pcode varchar2(30) not null,
	pname varchar2(30) not null,
	mcode varchar2(30) not null,
	constraint professor_pcode_pk primary key(pcode),
	constraint professor_mcode_fk foreign key(mcode) references major(mcode)
);

-- Test data 생성
insert into professor values('softnam', '남원홍', 'soft');
insert into professor values('softji', '지정희', 'soft');
insert into professor values('comlim', '임창훈', 'com');

commit;

select * from professor;

-- lecture table

drop table lecture cascade constraint;

create table lecture (
	lcode varchar2(30) not null,
	lname varchar2(30) not null,
	mcode varchar2(30) not null,
	pcode varchar2(30) not null,
	lday varchar2(30) not null,
	lstart varchar2(30) not null,
	lend varchar2(30) not null,
	lcount number(10) not null,
	lcurrent number(10) not null,
	constraint lecture_lcode_pk primary key(lcode),
	constraint lecture_mcode_fk foreign key(mcode) references major(mcode),
	constraint lecture_pcode_fk foreign key(pcode) references professor(pcode)
);

-- Test data 생성
insert into lecture values('1111','소프트웨어공학','soft', 
						'softnam', '월', '13:00', '15:00', 30, 0);
insert into lecture values('2222','데이터마이닝','soft', 
						'softji', '수', '14:00', '16:00', 35, 0);
insert into lecture values('1133','신호처리','com', 
						'comlim', '목', '9:00', '11:00', 50, 0);						

commit;

select * from lecture;

-- registration table

drop table registration cascade constraint;

create table registration (
	rnum number(10) not null,
	scode varchar2(30) not null,
	lcode varchar2(30) not null,
	constraint registration_rnum_pk primary key(rnum),
	constraint registration_scode_fk foreign key(scode) references student(scode),
	constraint registration_lcode_fk foreign key(lcode) references lecture(lcode)
);
create sequence registration_seq_rnum;

-- Test data 생성
insert into registration values(registration_seq_rnum.nextval, '201314135', '2222');
insert into registration values(registration_seq_rnum.nextval, '201314134', '1111');				
insert into registration values(registration_seq_rnum.nextval, '201212111', '1133');	

commit;

select * from registration;