create database c0222k1_management;
use c0222k1_management;

create table users(
	id BIGINT auto_increment primary key,
    username VARCHAR(255) UNIQUE,
    `password` VARCHAR(255),
	fullname VARCHAR(255),
    phone VARCHAR(255) UNIQUE,
	email  VARCHAR(255) UNIQUE,
    cityId INT,
    `Role` Enum("ADMIN", "USER"),
	updatedAt DATE ,
    createdAt  DATETIME,
    img BLOB,

	FOREIGN KEY( cityId) REFERENCES cities(id)

);



DELIMITER $$
CREATE PROCEDURE sp_insert_product (
	IN title_in VARCHAR(255),
    IN price_in DECIMAL(12,0),
    IN quantity_in INT,
    IN idCategory_in INT,
    IN size_in INT,
    IN color_in VARCHAR(255),
	OUT success TINYINT(1),
	OUT message VARCHAR(255)
)
BEGIN
	SET success = FALSE;
    
    IF(price_in < 0 OR price_in > 9999999999)
		THEN SET message ="Giá Tiền Nhập Vào Không Hợp Lệ";
        ELSE IF(quantity_in < 0 OR quantity_in > 1000)
        THEN SET message ="Số Lượng Nhập Vào Từ 0 đến 1000";
        
		ELSE IF(size_in < 38 OR size_in > 45)
        THEN SET  message ="Size nằm trong 38 đến 45";
        
        ELSE IF(NOT EXISTS (SELECT * FROM category WHERE id = idCategory_in))
        THEN SET message= "Danh Mục Không Tồn Tại";
        
        ELSE 
			INSERT INTO `c0222k1_management`.`product` (`title`, `price`, `quantity`, `createAT`, `idCategory`, `size`, `color`) 
			VALUES (title_in, price_in, quantity_in, now(), idCategory_in, size_in , color_in);
			SET message = "Thêm Sản Phẩm Thành Công";
             SET success = TRUE;
				END IF;
			END IF;
		END IF;
	END IF;
END
$$

set @success = 0;
set @message = '0';
call sp_insert_product('GiayVipDEP', 15211, 111, 5, 43, 'REd', @success, @message);
select @success, @message;


  





-- INSERT INTO `c0222k1_management`.`users` (`username`, `password`, `fullname`, `phone`, `email`, `City`, `role`, `updatedAt`) 
-- VALUES ('BV', '1', 'Bqv', '1213a1', 'vaasaa@gmail.com', 'Hue', 'USER', now());

-- SELECT u.id , u.username, u.password, u.fullname, u.phone, u.email, u.City, u.Role ,u.img 
-- FROM users AS u 
-- WHERE u.id = 44;

--  SELECT u.id , u.username, u.password, u.fullname, u.phone,u.email, c.name , u.Role, u.updatedAt,u.createdAt, u.img
--  FROM users AS u
--  JOIN cities AS c
--  ON u.cityId = c.id;

-- create table users(
-- 	id BIGINT auto_increment primary key,
-- 	fullname VARCHAR(255),
--     phone VARCHAR(255) ,
-- 	email  VARCHAR(255) 
-- );


-- SELECT * FROM c0222k1_management.product;

-- drop table users;
-- insert into users(Username, `Password`, FullName, Email,Phone,Address,Gender,`Role`,createdAt,updatedAt)
-- values('BQV','123456','BQVinh','Topxomray@gmail.com','0837762135','Hue',1,'ADMIN',now(),now() );
-- `Password`, `FullName`, `Email`, `Phone`, `Address`, `Gender`

