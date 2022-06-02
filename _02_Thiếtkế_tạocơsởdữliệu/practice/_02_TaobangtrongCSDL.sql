CREATE DATABASE QuanLyDiemThi;

USE QuanLyDiemThi;

CREATE TABLE HocSinh(
	MaHS VARCHAR(20) PRIMARY KEY,
	TenHS VARCHAR(50),
    NgaySinh DATETIME,
	Lop VARCHAR(20),
    GT VARCHAR(20)
);

CREATE TABLE MonHoc(
	MaMH VARCHAR(20) PRIMARY KEY,
	TenMH VARCHAR(50)
);

CREATE TABLE BangDiem(
	MaHS VARCHAR(20),
    MaMH VARCHAR(20),
    DiemThi INT,
    NGAYKT DATETIME,
    PRIMARY KEY(MaHS,MaMH),
    FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS),
    FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH)
);

CREATE TABLE GiaoVien(
	MaGV VARCHAR(20),
    TenGV VARCHAR(255),
    SDT  VARCHAR(15)
);

ALTER TABLE MonHoc ADD
	MaGV VARCHAR(255);
    
ALTER TABLE MonHoc ADD
	FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV);
