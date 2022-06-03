delimiter $$
create procedure gui_tien(
	IN id_customer BIGINT,
    IN money_send decimal(12,0),
    OUT mes VARCHAR(255)
)
BEGIN
	DECLARE total BIGINT;
	IF(not exists 
		(select * from customers 
        where id = id_customer ))
        THEN SET mes = "Không tìm thấy ID muốn gửi";
        ELSE
			INSERT INTO `banking_transaction_management`.`deposits` (`created_at`, `deleted`, `updated_at`, `customer_id`, `transaction_amount`) VALUES 
            ('now()', '0', 'now()', id_customer, money_send);
       SET total =(select balance from customers where id = id_customer);
       
       UPDATE customers
       set balance = total + money_send
       where id = id_customer;
       
		END IF;
	
END;$$

CALL gui_tien;