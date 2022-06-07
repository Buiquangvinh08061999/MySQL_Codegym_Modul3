
-- Phần Product(Thêm sản phẩm , xóa sản phẩm , cập nhật lại sản phẩm;
delimiter $$
CREATE PROCEDURE add_products(
    IN id_product BIGINT,
    IN title VARCHAR(255),
    IN price decimal(12,0),
    IN quantity INT,
    IN description_products VARCHAR(255),
    OUT mes VARCHAR(255)
)
BEGIN
IF(exists( SELECT id  FROM products WHERE id = id_product))
	THEN SET mes = "ID đã tồn tại không thể thêm sản phẩm";
		ELSE 
IF(id_product <=0)
	THEN SET mes ="Yêu cầu nhập id lại , không tạo id này";
    ELSE
		INSERT INTO `casestudy2`.`products` (`id`, `title`, `price`, `quantity`, `description_products`,`createdAt` , `updatedAt`)
        VALUES (id_product, title , price, quantity, description_products, now(), now() );
        
        SET mes = " Thêm sản phẩm thành công";
        
	
END IF;
END IF;

END;$$

DROP PROCEDURE add_products;

CALL add_products(5,"Dây sạc",150000,10,"Đẹp",@mes);
SELECT @mes;





delimiter $$
CREATE PROCEDURE remove_products(
    IN id_product BIGINT,
    OUT mes VARCHAR(255)
)
BEGIN
	IF(NOT EXISTS (SELECT id FROM  products WHERE id = id_product ))
		THEN SET mes = "ID không tồn tại";
        ELSE
        
			DELETE FROM products 
            WHERE id = id_product;
            SET mes = " Xóa thành công" ;
            
        END IF;
        
END;$$

DROP  PROCEDURE remove_products;

CALL remove_products(1,@mes);
SELECT @mes;


delimiter $$
CREATE PROCEDURE update_products(
    IN id_product BIGINT,
	IN title_product  VARCHAR(255),
    IN price_product  decimal(12,0),
    IN quantity_product  INT,
    IN description_product VARCHAR(255),
    OUT mes VARCHAR(255)
)
BEGIN
	IF(NOT EXISTS (SELECT id FROM products where id = id_product) )
		THEN SET mes = "Không tìm thấy ID muốn sửa";
		ELSE
        
        UPDATE products
        SET title = title_product, 
        price = price_product,
        quantity = quantity_product,
        description_products = description_product
        WHERE id = id_product;
        
        SET mes ="Sửa sản phẩm thành công";
        
	END IF;
END;$$

drop procedure update_products;

CALL update_products(2,"Sạc đa năng", 150000, 100,"Chất lượng",@mes);
SELECT @mes;



