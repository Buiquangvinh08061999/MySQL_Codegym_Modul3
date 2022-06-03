delimiter $$
create procedure add_new(
	in id_customer bigint,
    in amount decimal(12,0),
    out mes nvarchar(255)
)
  begin
		declare total bigint;
		if(not exists (
			select *
            from customers
            where id = id_customer
        ))
        then set mes = "no customer";
        else
			INSERT INTO `banking`.`deposits` (`created_at`, `deleted`, `updated_at`, `customer_id`, `transaction_amount`)
            VALUES (now(), '0', now(), id_customer, amount);
            set total = (
				select balance
                from customers
                where id = id_customer
            );
            update customers
            set balance = total + amount
            where id = id_customer;
		end if;
	end;$$
    
call add_new(1,12345,@mes);
select @mes;

delimiter $$
create procedure draw(
	in amount_draw bigint,
    in id_customer int,
    out mes nvarchar(255)
)
begin
	declare total bigint;
	if(not exists (
			select *
            from customers
            where id = id_customer
        ))
        then set mes = "no customer";
        else
			if(amount_draw > (select balance from customers where id = id_customer))
				then set mes = "blance > amoun";
			else
                INSERT INTO `banking`.`withdraws` (`created_at`, `deleted`, `updated_at`, `customer_id`, `transaction_amount`)
					VALUES (now(), '0', now(), id_customer, amount_draw);
				set total = (
					select balance
					from customers
					where id = id_customer
				);
				update customers
				set balance = total - amount_draw
				where id = id_customer;
				end if;
        end if;
end
$$
call draw(400,7,@mes);
select @mes;
/**
DELIMITER //
CREATE PROCEDURE find_all(in id int)
BEGIN
	SELECT *
    FROM deposits
    WHERE id = 10;
END;//
DELIMITER ;
**/
delimiter $$
	
    create procedure send(
		in id_custom1 int ,
        in id_custom2 int ,
        in money_send bigint,
        out mes nvarchar(255)
    )
    begin
    declare total_money1 bigint;
    declare total_money2 bigint;
    if(not exists (select * from customers where id = id_custom1) or not exists (select * from customers where id = id_custom2))
		then set mes = "Khong the chuyen do 1 trong hai khach hang khong ton tai" ;
	else if ((select id from customers where id = id_custom1) = (select id from customers where id = id_custom2))
		then set mes = "trung nhau";
    else
		set total_money1 = (select balance from customers where id = id_custom1);
        if(money_send = 0)
           then set mes = " tien chuyen phai > 0";
        else if(total_money1 <= money_send)
           then set mes = "tien khong du de chuyen vi chua tinh 10%";
		else
			if(total_money1 - (money_send + money_send * 10 / 100) < 0)
                then set mes = " tien khong du vi tinh 10% ma cung bi am tien " ;
			else
				INSERT INTO `banking`.`transfers` (`deleted`, `updated_at`, `updated_by`, `fees`, `fees_amount`, `transaction_amount`, `transfer_amount`, `recipient_id`, `sender_id`)
                VALUES ('0', now(), now(),10,money_send * 10 / 100, money_send + money_send * 10 / 100,money_send,id_custom1,id_custom2);
                update customers
                set balance = total_money1 - (money_send + money_send * 10 / 100)
                where id = id_custom1
                ;
                set total_money2 = (select balance from customers where id = id_custom2);
                update customers
                set balance = (total_money2  + money_send)
                where id = id_custom2
                ;
                end if;
                end if;
                end if;
                end if;
                end if;
    end
$$
call send(9,10,390,@mes);
select @mes