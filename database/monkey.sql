drop table if exists monkey;
create table monkey(
  id int(10) primary key auto_increment,
  name varchar(20) not null unique ,
  birthday DATETIME
);

desc monkey;