drop table if exists monkey;
create table monkey(
  id int(10) primary key auto_increment,
  name varchar(20) not null unique ,
  birthday DATETIME
);

desc monkey;

select * from monkey;

select id, name from monkey where name like 'J%';

insert into monkey(name)
  values ('M1');
insert into monkey(name)
  values ('M2');
insert into monkey(name)
  values ('M3');
insert into monkey(name)
  values ('M4');
insert into monkey(name)
  values ('M5');
insert into monkey(name)
  values ('M6');
insert into monkey(name)
  values ('M7');

select * from monkey
  order by id
  limit 4 offset 4;