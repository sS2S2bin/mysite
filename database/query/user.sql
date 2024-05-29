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

select * from board;
insert into board( null , "첫번째","첫번째\n내용", 1, now(), 1, 1, 0, 4);


