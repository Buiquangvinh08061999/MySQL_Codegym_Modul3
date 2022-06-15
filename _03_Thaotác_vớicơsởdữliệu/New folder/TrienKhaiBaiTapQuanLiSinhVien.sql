SELECT * FROM quanlysinhvien_03.student;

-- Bài 1: Hiển thị tất cả học sinh-- 
SELECT * FROM student;

-- Bài 2: Hiển thị danh sách các học viên đang theo học 0:Flase, 1:True--
SELECT * FROM student
WHERE Status = true;

-- Bài 3: Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ
SELECT * FROM `subject`
WHERE Credit < 10;

-- Bài 4: Hiển thị danh sách học viên, studentID ,StudentName ,lớp A1
select S.StudentId, S.StudentName, ClassName 
FROM student S
INNER JOIN class C ON S.ClassId = C.ClassID
WHERE ClassName= "A1";

-- Bài 5: Hiển thị điểm môn CF của các học viên.
SELECT S.StudentId , S.StudentName , Sub.SubName , M.Mark
FROM student S
JOIN mark M ON S.StudentId = M.StudentId
JOIN `subject` Sub ON M.SubId = Sub.SubId
WHERE Sub.SubName ="CF";