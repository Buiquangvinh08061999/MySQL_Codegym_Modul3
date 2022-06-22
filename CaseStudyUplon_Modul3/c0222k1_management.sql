create database c0222k1_management;
use c0222k1_management;

create table users(
	id BIGINT auto_increment primary key,
    username VARCHAR(255) UNIQUE,
    `password` VARCHAR(255),
	fullname VARCHAR(255),
    phone VARCHAR(255) UNIQUE,
	email  VARCHAR(255) UNIQUE,
    City VARCHAR(255),
    `Role` Enum("ADMIN", "USER"),
	updatedAt DATE ,
    createdAt  DATETIME,
    img BLOB
);
INSERT INTO `c0222k1_management`.`users` (`username`, `password`, `fullname`, `phone`, `email`, `City`, `role`, `updatedAt`) 
VALUES ('BV', '1', 'Bqv', '1213a1', 'vaasaa@gmail.com', 'Hue', 'USER', now());

SELECT u.id , u.username, u.password, u.fullname, u.phone, u.email, u.City, u.Role ,u.img 
FROM users AS u 
WHERE u.id = 44;



-- create table users(
-- 	id BIGINT auto_increment primary key,
-- 	fullname VARCHAR(255),
--     phone VARCHAR(255) ,
-- 	email  VARCHAR(255) 
-- );


drop table users;
-- insert into users(Username, `Password`, FullName, Email,Phone,Address,Gender,`Role`,createdAt,updatedAt)
-- values('BQV','123456','BQVinh','Topxomray@gmail.com','0837762135','Hue',1,'ADMIN',now(),now() );
-- `Password`, `FullName`, `Email`, `Phone`, `Address`, `Gender`

