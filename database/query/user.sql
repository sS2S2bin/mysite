desc user;

-- join
delete from user where no=1;
insert into user values(null,'관리자','admin@gmail.com',password('admin'),'female',current_date()); -- now()는 시간까지 나오기 때문
select * from user;
select * from user where email="admin@gmail.com" and password=password('admin');

insert into user values(null,'a','a@gmail.com',password('a'),'female',current_date());
update user 
set name='b' , gender='male' 
where no=6;


select max(g_no) from board;
select * from board where user_no=4;
select * from user where no=4;

select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name 
from board b, user u
where u.no = b.user_no
order by g_no desc, o_no asc;



-- hit 0, g_no 고정, oNo +1해서 추가, 3번째 답글 보다 위에 나타나야하며, 
insert into board values(null, "3번째 답글","첫번째\n내용", 1, now(), 9, 4, 2, 7);
select no, title, content, g_no, o_no,dept, user_no,reg_date
from board
;

select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name 
from board b, user u
where u.no = b.user_no
and b.no = 1
order by g_no desc, o_no asc;
select * from board;


delete
from board
where no=1
and user_no=5;

select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name,u.no as userNo
from board b, user u
where u.no = b.user_no
-- and b.no = 1
-- and b.user_no =3
order by g_no desc, o_no asc
limit 5;

select * from board where g_no=9;
select * from user;

update board 
set title='title로 바꿔랏.' , contents='다 읎어졋어예.' 
where no=2;

select g_no, o_no, depth
from board
where no = 11;

insert into board values(null, "2번째 답글에 대한 답글","첫번째\n내용", 1, now(), 9, 4, 2, 4);

select * from board 
where g_no=9
and depth = 2
and o_no >=4
;

update board
set o_no = o_no+1
where o_no>=4
and g_no = 9
and depth = 2
; 


select * from board
order by g_no desc, o_no asc;
-- 11, 18,8, 1


set SQL_SAFE_UPDATES=0;

select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name,u.no as userNo 
from board b, user u
where u.no = b.user_no
order by g_no desc, o_no asc
limit 15,5
;
-- 0,5,10,15

