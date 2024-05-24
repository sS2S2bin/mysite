desc user;

-- join
delete from user where no=1;
insert into user values(null,'관리자','admin@gmail.com',password('admin'),'female',current_date()); -- now()는 시간까지 나오기 때문
select * from user;