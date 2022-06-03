SELECT * FROM vattu.vattu_don_dathang;

SELECT Ngay_dathang, So_donhang
FROM vattu_don_dathang ;

CREATE VIEW ThongTin
AS
SELECT vt.Ma_vattu , vt.Ngay_dathang , dh.ma_nha_cung_cap, dh.So_donhang
FROM vattu_don_dathang vt
  INNER JOIN don_dat_hang dh ON vt.so_donhang = dh.so_donhang;

DROP VIEW ThongTin;

SELECT* FROM ThongTin;
