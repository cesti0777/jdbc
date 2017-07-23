-- major table
drop table major cascade constraint;

-- student table
drop table student cascade constraint;

-- professor table
drop table professor cascade constraint;

-- lecture table
drop table lecture cascade constraint;

-- registrationlist
drop view registrationlist cascade constraint;

-- lecturelist
drop view lecturelist cascade constraint;

-- registration table
drop table registration cascade constraint;
drop sequence registration_seq_rnum;
create sequence registration_seq_rnum;

-- major table
create table major(
	mcode varchar2(30) not null,
	mname varchar2(30) not null,
	constraint major_mnum_pk primary key(mcode)
);

-- student table
create table student(
	scode varchar2(30) not null,
	sname varchar2(30) not null,
	mcode varchar2(30) not null,
	sid varchar2(30) not null,
	spassword varchar2(30) not null,
	constraint student_scode_pk primary key(scode),
	constraint student_mcode_fk foreign key(mcode) references major(mcode)
);

-- professor table
create table professor (
	pcode varchar2(30) not null,
	pname varchar2(30) not null,
	mcode varchar2(30) not null,
	pclass varchar2(30) not null,
	constraint professor_pcode_pk primary key(pcode),
	constraint professor_mcode_fk foreign key(mcode) references major(mcode)
);

-- lecture table
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

-- registration table
create table registration (
	rnum number(10) not null,
	scode varchar2(30) not null,
	lcode varchar2(30) not null,
	constraint registration_rnum_pk primary key(rnum),
	constraint registration_scode_fk foreign key(scode) references student(scode),
	constraint registration_lcode_fk foreign key(lcode) references lecture(lcode)
);

-- registrationlist
create view registrationlist as 
select lcode, lname, m.mname, p.pname, lday, lstart, lend
from lecture l, major m, professor p
where l.mcode = m.mcode and l.pcode = p.pcode;

-- lecturelist
create view lecturelist as 
select lcode, lname, m.mname, p.pname, lday, lstart, lend, lcount, lcurrent
from lecture l, major m, professor p
where l.mcode = m.mcode and l.pcode = p.pcode;




