-- create database Alias_student;

-- drop database Alias_student;

use Alias_student;


create table student(
	StudentId int auto_increment primary key,
    `name` varchar(255),
    gender BIT,
    dob DATE 
);

 
-- insert into
-- student(name,gender,dob)
-- values ('BQV',0,'1996-08-06');

-- SELECT * FROM student
-- WHERE studentId = "9";

-- Hàm update theo id
UPDATE student
SET name = ?, gender = ?, dob =?
WHERE StudentId = ?;

-- Hàm xóa theo id
-- DELETE FROM student WHERE StudentId = ?;

-- drop table student;


















-- SELECT * FROM student;

-- DELETE FROM student
-- WHERE StudentId = ?;


-- UPDATE student 
-- SET gender = 0
-- WHERE StudentId = 11;
 














