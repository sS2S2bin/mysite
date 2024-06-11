alter table user add column role enum('ADMIN', 'USER') not null default 'USER';

select * from user;
