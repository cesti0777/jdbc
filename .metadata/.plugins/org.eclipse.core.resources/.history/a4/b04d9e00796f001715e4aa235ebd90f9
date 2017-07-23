-- major data 생성
insert into major values('soft', '소프트웨어학과');
insert into major values('com', '컴퓨터공학과');
insert into major values('busi', '경영학과');

-- student data 생성
insert into student values('201314135', '백경진', 'soft', 'gpfqk', '1234');
insert into student values('201314134', '박지빈', 'soft', 'cesti0777', '1234');
insert into student values('201212123', 'student3', 'soft', 'king7854', '1234');
insert into student values('201412124', 'student4', 'soft', 'stu4', '1234');
insert into student values('201512125', 'student5', 'soft', 'stu5', '1234');
insert into student values('201512129', 'student6', 'soft', 'stu6', '1234');


-- professor data 생성
insert into professor values('softnam', '남원홍', 'soft', '부교수');
insert into professor values('softji', '지정희', 'soft', '부교수');
insert into professor values('comlim', '임창훈', 'soft', '정교수');
insert into professor values('comkim', '김형석', 'soft', '부교수');
insert into professor values('comkei', '김은이', 'com', '정교수');
insert into professor values('busiksy', '박소영', 'com', '부교수');
insert into professor values('busilhw', '이향원', 'com', '정교수');
insert into professor values('busijkj', '정갑주', 'com', '부교수');
insert into professor values('busikji', '김지인', 'busi', '정교수');
insert into professor values('busilmk', '임민규', 'busi', '부교수');
insert into professor values('busijjw', '장진욱', 'busi', '정교수');



-- lecture data 생성
insert into lecture values('1111','소프트웨어공학','soft', 'softnam', '월', '13:00', '14:50', 5, 0);
insert into lecture values('2222','데이터마이닝','soft', 'softji', '화', '14:00', '15:50', 5, 0);
insert into lecture values('3333','신호처리','soft', 'comlim', '수', '09:00', '10:50', 5, 0);		
insert into lecture values('4444','컴퓨터그래픽스','soft', 'comkim', '수', '10:00', '10:50', 5, 0);	
insert into lecture values('5555','컴퓨터구조','com', 'comkei', '월', '09:00', '10:50', 5, 4);	
insert into lecture values('6666','c프로그래밍','com', 'busiksy', '화', '14:00', '15:50', 5, 3);	
insert into lecture values('7777','java프로그래밍','com', 'busilhw', '수', '09:00', '10:50', 5,5);	
insert into lecture values('8888','웹프로그래밍','com', 'busijkj', '목', '15:00', '16:50', 5, 1);	
insert into lecture values('9999','경영정보','busi', 'busikji', '월', '09:00', '10:50', 5, 2);	
insert into lecture values('0000','경영전략','busi', 'busilmk', '화', '14:00', '15:50', 5,4);	
insert into lecture values('1122','생산관리','busi', 'busijjw', '수', '13:00', '14:50', 5, 5);	


-- registration data 생성

insert into registration values(registration_seq_rnum.nextval, '201314134', '3333');
update lecture set lcurrent = lcurrent+1 where lcode = '3333';
insert into registration values(registration_seq_rnum.nextval, '201314135', '1111');
update lecture set lcurrent = lcurrent+1 where lcode = '1111';
insert into registration values(registration_seq_rnum.nextval, '201314135', '2222');
update lecture set lcurrent = lcurrent+1 where lcode = '2222';
insert into registration values(registration_seq_rnum.nextval, '201314135', '4444');
update lecture set lcurrent = lcurrent+1 where lcode = '4444';
insert into registration values(registration_seq_rnum.nextval, '201212123', '1111');
update lecture set lcurrent = lcurrent+1 where lcode = '1111';
insert into registration values(registration_seq_rnum.nextval, '201212123', '2222');	
update lecture set lcurrent = lcurrent+1 where lcode = '2222';
insert into registration values(registration_seq_rnum.nextval, '201212123', '4444');
update lecture set lcurrent = lcurrent+1 where lcode = '4444';
insert into registration values(registration_seq_rnum.nextval, '201412124', '1111');	
update lecture set lcurrent = lcurrent+1 where lcode = '1111';
insert into registration values(registration_seq_rnum.nextval, '201412124', '2222');	
update lecture set lcurrent = lcurrent+1 where lcode = '2222';
insert into registration values(registration_seq_rnum.nextval, '201412124', '3333');	
update lecture set lcurrent = lcurrent+1 where lcode = '3333';
insert into registration values(registration_seq_rnum.nextval, '201412124', '1111');
update lecture set lcurrent = lcurrent+1 where lcode = '1111';
insert into registration values(registration_seq_rnum.nextval, '201412124', '2222');
update lecture set lcurrent = lcurrent+1 where lcode = '2222';
insert into registration values(registration_seq_rnum.nextval, '201412124', '3333');
update lecture set lcurrent = lcurrent+1 where lcode = '3333';
insert into registration values(registration_seq_rnum.nextval, '201512129', '1111');
update lecture set lcurrent = lcurrent+1 where lcode = '1111';

commit;

