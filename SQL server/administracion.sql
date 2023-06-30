create table user (
id int not null auto_increment,
name varchar (50) not null,
edad int not null,
email varchar(100) not null,
primary key (id)
);

insert into user (name, edad, email) values ('oscar', 25, 'oscar@gmail.com');
insert into user (name, edad, email) values ('layla', 15, 'Layla@gmail.com');
insert into user (name, edad, email) values ('Nicolas', 36, 'Nicolas@gmail.com');
insert into user (name, edad, email) values ('Juan', 55, 'Juan@gmail.com');

select * from  user;
select * from user limit 1;
select * from user where edad > 15;
select * from user where edad >= 15;
select * from user where edad > 20 and email = 'Nicolas@gmail.com';
select * from user where edad > 20 or email = 'Layla@gmail.com';
select * from user user where email != 'Layla@gmail.com';
select * from user where edad between 15 and 30;
select * from user where email like '%gmail%';
select * from user where email like '%gmail';
select * from user where email like 'oscar%';

select * from user order by edad asc;
select * from user order by edad desc;
select * from user order by id asc;

select max(edad) as mayor from user;
select min(edad) as menor from user;

select id, name from user ;
select id, name as NPersona from user;

