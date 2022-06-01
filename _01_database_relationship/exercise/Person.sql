create database baitap_1;

use baitap_1;

create table students(
	id int,
    name_student varchar(255),
    age int not null,
    country varchar(255)
);


create table  class (
    id_of_class int,
    nameOfClass varchar(255)
);

create table teacher (
    id int,
    name_of_teacher varchar(255),
    age INT,
    country varchar(255)
);

insert into students values(1,"Vinh",15,"Hue");
insert into students values(2,"Vinh",15,"Hue");

insert into class values(1,"MATH");

insert into teacher values (1,"Vip",24,'viet nam');






