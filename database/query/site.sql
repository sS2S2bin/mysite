-- insert
desc site;
insert into site values(null,'MySite','안녕하세요 이구빈의 mysite에 오신 것을 환영합니다.','/statics/images/baby.jpeg' , '이 사이트는 웹 프로그래밍 및 실습 과제 예제를 위한 사이트입니다.\n 자바,스프링을 배우고 있어요'); 
select * from site;

update site set profile="/assets/upload-images/202451845449478.jpeg";

select * from user;
select * from board;

select no,name,password,contents as content,date_format(reg_date, '%Y/%m/%d %H:%i:%s') as regDate
	from guestbook 
	order by reg_date desc;


select no, name, email, gender
		  from user
		 where email = 'd';
         
         		select no, name, email, gender
		  from user
		 where email = 'd'