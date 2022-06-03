CREATE DATABASE VatTu;
DROP DATABASE VatTu;
USE VatTu;

CREATE TABLE phieu_xuat(
	SoPX INT NOT NULL PRIMARY KEY,
    NgayXuat DATE
);
INSERT INTO phieu_xuat(SoPX,NgayXuat)
VALUES (1,NOW());

CREATE TABLE phieu_nhap(
	SoPN INT NOT NULL PRIMARY KEY,
    NgayNhap DATE
);
INSERT INTO phieu_nhap(SoPN,NgayNhap)
VALUES (1,NOW());

CREATE TABLE vat_tu(
	Ma_vattu INT NOT NULL PRIMARY KEY,
    Ten_vattu VARCHAR(255) NOT NULL 
);
INSERT INTO vat_tu(Ma_vattu,Ten_vattu)
VALUES (1,"Xi măng");

CREATE TABLE don_dat_hang(
	So_donhang INT NOT NULL PRIMARY KEY,
    Ngay_dathang DATE,
    ma_nha_cung_cap INT NOT NULL,
    FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap(ma_nha_cung_cap)
);
INSERT INTO don_dat_hang(So_donhang ,Ngay_dathang,ma_nha_cung_cap)
VALUES (1,now(), 1);


CREATE TABLE nha_cung_cap(
	ma_nha_cung_cap INT NOT NULL PRIMARY KEY,
    ten_nha_cung_cap  VARCHAR(255) NOT NULL,
    dia_chi VARCHAR(255) NOT NULL
);
INSERT INTO nha_cung_cap(ma_nha_cung_cap, ten_nha_cung_cap, dia_chi)
VALUES(1,"POHALIFE","Ngu Hanh Son");


CREATE TABLE phieuxuat_vattu(
	don_gia_xuat BIGINT NOT NULL,
    so_luong_xuat INT  NOT NULL,
    SoPX INT NOT NULL,
    NgayXuat DATE,
    Ma_vattu INT NOT NULL,
    Ten_vattu VARCHAR(255),
    PRIMARY KEY(SoPX,Ma_vattu),
    FOREIGN KEY (SoPX) REFERENCES phieu_xuat(SoPX),
    FOREIGN KEY (Ma_vattu) REFERENCES vat_tu(Ma_vattu)
);
DROP TABLE phieuxuat_vattu;
INSERT INTO phieuxuat_vattu(don_gia_xuat, so_luong_xuat, SoPX, NgayXuat,Ma_vattu, Ten_vattu )
VALUES(232133,1,1,now(),1,"Xi mang");

CREATE TABLE phieunhap_vattu(
	don_gia_nhap BIGINT NOT NULL,
    so_luong_nhap INT  NOT NULL,
    SoPN INT NOT NULL,
    NgayNhap DATE,
	Ma_vattu INT NOT NULL,
    Ten_vattu VARCHAR(255),
    PRIMARY KEY(SoPN, Ma_vattu),
    FOREIGN KEY(SoPN) REFERENCES phieu_nhap(SoPN),
    FOREIGN KEY(Ma_vattu) REFERENCES vat_tu(Ma_vattu)
);
DROP TABLE phieunhap_vattu;
INSERT INTO phieunhap_vattu(don_gia_nhap, so_luong_nhap , SoPN ,  NgayNhap , Ma_vattu , Ten_vattu)
VALUES (123424,1,1,now(),1,"Xi mang");


CREATE TABLE vattu_don_dathang(
	Ngay_dathang DATE,
    Ten_vattu VARCHAR(255),
    So_donhang INT  NOT NULL,
    Ma_vattu INT NOT NULL,
	PRIMARY KEY(So_donhang, Ma_vattu),
    FOREIGN KEY(So_donhang) REFERENCES don_dat_hang(So_donhang),
    FOREIGN KEY(Ma_vattu) REFERENCES vat_tu(Ma_vattu)
);
INSERT INTO vattu_don_dathang(Ngay_dathang, Ten_vattu, So_donhang ,Ma_vattu)
VALUES (now(),"Xi Mang",1,1);

CREATE TABLE so_dien_thoai(
	ma_nha_cung_cap  INT PRIMARY KEY,
    so_dien_thoai VARCHAR(255),
    FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap(ma_nha_cung_cap)
);

INSERT INTO so_dien_thoai(ma_nha_cung_cap, so_dien_thoai)
VALUES (1,0837762135);



