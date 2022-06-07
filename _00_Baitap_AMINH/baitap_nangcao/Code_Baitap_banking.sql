
USE banking_transaction_management;



delimiter $$
create procedure gui_tien(
	IN id_customer BIGINT,
    IN money_send decimal(12,0),
    OUT mes VARCHAR(255)
)
BEGIN
	DECLARE total BIGINT;
	IF(NOT EXISTS(SELECT * FROM customers WHERE id = id_customer ))
        THEN SET mes = "Không tìm thấy ID muốn gửi";
		ELSE
    IF(money_send <= 0 )
		THEN SET mes ="Tiền chuyển phải lớn 0";
		ELSE
	INSERT INTO `banking_transaction_management`.`deposits` (`created_at`, `deleted`, `updated_at`, `customer_id`, `transaction_amount`) VALUES 
		(now(), '0', now(), id_customer, money_send);
        
       SET total = (select balance from customers where id = id_customer);
       
       UPDATE customers
       SET balance = total + money_send
       WHERE id = id_customer;
	
	   SET mes =  "Đã chuyển tiền thành công";
       
    END IF;   
	END IF;

END;$$

CALL gui_tien(1,5000,@mes);
SELECT @mes;


delimiter $$
CREATE PROCEDURE rut_tien(
	IN  id_customer INT,
	IN 	money_out decimal(12,0),
	OUT mes VARCHAR(255)
)
BEGIN 
	DECLARE total BIGINT;
	IF(NOT EXISTS(SELECT * FROM customers WHERE id = id_customer))
		THEN SET mes = "Không tìm thấy ID";
        ELSE 
	IF(money_out > (SELECT balance FROM customers WHERE id = id_customer))
		THEN SET mes = "Số tiền rút lớn hơn số tiền trong tài khoản";
		ELSE
	IF(money_out <= 0)
        THEN SET mes ="Vui lòng nhập số tiền rút lớn hơn 0";
        ELSE
	IF((SELECT balance FROM customers WHERE id = id_customer) = 0)	
         THEN SET mes ="Số tiền trong tài khoản là 0 tiền ";
		ELSE
	INSERT INTO `banking_transaction_management`.`withdraws` (`created_at`, `deleted`, `updated_at`, `customer_id`, `transaction_amount`) VALUES 
    (now(), 0, now(), id_customer, money_out);	
	
	SET total = (SELECT balance FROM customers WHERE id = id_customer);
	
    UPDATE customers
    SET balance = total - money_out
    WHERE id = id_customer;
	SET mes = " Rút tiền thành công";
     
	END IF;    
	END IF;   
	END IF;
	END IF;
		
END;$$

CALL rut_tien(1,3000,@mes);
SELECT @mes;




delimiter $$
CREATE PROCEDURE chuyen_tien(
	IN id_custom1 INT,
    IN id_custom2 INT,
    IN money_send BIGINT,
	OUT mes VARCHAR(255)
)
BEGIN
	DECLARE total_money1 BIGINT;
    DECLARE total_money2 BIGINT;
	IF(NOT EXISTS (SELECT * FROM customers WHERE id = id_custom1) OR NOT EXISTS ( SELECT * FROM customers WHERE id = id_custom2) )
		THEN SET mes = "Không tìm thấy ID của 1 trong 2 ID này";
        ELSE 
    IF((SELECT * FROM customers WHERE id = id_custom1) = (SELECT * FROM customers WHERE id = id_custom2) )
		THEN SET mes ="Không thể tự chuyển cho chính mình, ID bị trùng lặp";
        ELSE
			SET total_money1 = (SELECT balance FROM customers WHERE id = id_custom1);
            SET total_money2 = (SELECT balance FROM customers WHERE id = id_custom2);
	IF(money_send <= 0)
		THEN SET mes = "Tiền gửi phải lớn hơn 0";
        ELSE
    IF(total_money1 <= money_send)
		THEN SET mes = "Tài khoản không đủ để chuyển tiền vì phải tính thêm 10% phí";
        ELSE
	IF(total_money1 - (money_send + money_send *10/100) < 0)
		THEN SET mes = "Tiền không đủ vì tính thêm 10% cũng bị âm tiền";
        ELSE
	INSERT INTO `banking_transaction_management`.`transfers` (`created_at`,`deleted`, `updated_at`, `fees`, `fees_amount`, `transaction_amount`, `transfer_amount`, `sender_id`,`recipient_id`) 
    VALUES (now(), '0', now() , 10 , money_send * 10/100 , money_send + money_send * 10/100, money_send, id_custom1 ,id_custom2 );
        
    -- Cập nhật lại thằng gửi , lấy tổng tiền - số tiền gửi + thêm số tiền 10% phí;    
    UPDATE customers
    SET balance = total_money1 - (money_send + money_send * 10/100)
    WHERE id = id_custom1;
    
    -- Cập nhật lại thằng nhận cộng thêm tiền nhận vào và tổng lên;
	UPDATE customers
    SET balance = total_money2 + money_send
    WHERE id = id_custom2; 
    
    SET mes ="Chuyển tiền thành công";
    
	END IF;	   
	END IF;	
	END IF;
	END IF; 
	END IF; 
END;$$

DROP PROCEDURE chuyen_tien;

CALL chuyen_tien(11,8,50,@mes);
SELECT @mes;








delimiter $$
CREATE TRIGGER update_customer_from_deposits
AFTER INSERT
ON deposits FOR EACH ROW
BEGIN
	DECLARE total DECIMAL(12,0);
    SET total = (SELECT balance FROM customers WHERE id = NEW.customer_id);
    
    UPDATE `banking_transaction_management`.`customers` 
    SET `balance` = total + NEW.transaction_amount , `updated_at` = now() WHERE (`id` = NEW.customer_id);

END;$$

delimiter $$
CREATE TRIGGER delete_customer_from_withdraws
AFTER INSERT
ON withdraws FOR EACH ROW
BEGIN
	DECLARE total DECIMAL(12,0);
    SET total = (SELECT balance FROM customers WHERE id = NEW.customer_id);

END;$$





































