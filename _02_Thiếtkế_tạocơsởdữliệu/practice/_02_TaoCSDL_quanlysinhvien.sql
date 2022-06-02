CREATE DATABASE QuanLySinhVien;
USE QuanLySinhVien;

CREATE TABLE Class(
	ClassID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ClassName VARCHAR(60) NOT NULL,
    StartDate DATETIME NOT NULL,
    `Status` BIT 
);
INSERT INTO Class (ClassName, StartDate )
VALUES("C0222K1", now());
INSERT INTO Class (ClassName, StartDate, `Status`)
VALUES("C0222K1", now(),0);

CREATE TABLE Student(
	StudentID INT AUTO_INCREMENT PRIMARY KEY,
	StudentName VARCHAR(30) NOT NULL,
    Address VARCHAR(50),
    Phone VARCHAR(20),
    `Status` BIT,
    ClassID INT NOT NULL,
    FOREIGN KEY(ClassID) REFERENCES Class(ClassID)
);
INSERT INTO Student (StudentName,Address,Phone,`Status` ,ClassID)
VALUES("Bùi Quang Vinh","Huế",0837762135,1,1);

CREATE TABLE `Subject`(
	SubID INT AUTO_INCREMENT PRIMARY KEY,
    SubName VARCHAR(30) NOT NULL,
    Credit TINYINT NOT NULL,
   `Status` BIT
);
INSERT INTO `Subject`(SubName,  Credit)
VALUES("Vinh",2);



CREATE TABLE Mark(
	MarkID INT AUTO_INCREMENT PRIMARY KEY,
    SubID INT NOT NULL UNIQUE ,
	StudentID INT NOT NULL UNIQUE,
    Mark FLOAT,
    ExamTimes TINYINT DEFAULT 1,
	FOREIGN KEY(SubID) REFERENCES  `Subject`(SubID),
    FOREIGN KEY(StudentID) REFERENCES Student(StudentID)
);

INSERT INTO Mark(SubID,StudentID,Mark,ExamTimes)
VALUES (1,1,1111,2)



