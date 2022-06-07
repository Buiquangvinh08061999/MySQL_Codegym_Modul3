

create database casestudy2;
USE casestudy2;


CREATE TABLE products(
	id INT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(12,0),
    quantity INT NOT NULL,
    description_products VARCHAR(255),
    createdAt DATE,
    updatedAt DATE,
	PRIMARY KEY(id)
);
drop table products;

INSERT INTO products(title,price,quantity,description_products,createdAt,updatedAt)
VALUES ("Cáp sạc",150000,5,"ngon",now(),now() );
INSERT INTO products(title,price,quantity,description_products,createdAt,updatedAt)
VALUES ("Tai Nghe",50000,1,"ngon",now(),now()  );

CREATE TABLE users(
	id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    fullname VARCHAR(255),
    mobile VARCHAR(255),
    email VARCHAR(255) UNIQUE,
	address VARCHAR(255),
    role_users VARCHAR(255),
	createdAt DATE,
    updatedAt DATE
);
drop table  users;
INSERT INTO users(username,fullname,mobile,email,address, role_users,createdAt,updatedAt)
VALUES ("Vinh","Quang",0837762135,"topxomray@gmail.com","Huế","ADMIN",now(), now() );

INSERT INTO users(username,fullname,mobile,email,address, role_users,createdAt, updatedAt)
VALUES ("TOp","Quang",0837762135,"topxom@gmail.com","Huế","ADMIN", now() , now() );

CREATE TABLE orders(
	id INT AUTO_INCREMENT,
    fullname VARCHAR(255),
    mobile VARCHAR(255),
    address VARCHAR(255),
    createdAt DATE,
	PRIMARY KEY(id,fullname)
);
drop table orders;
INSERT INTO orders(fullname,mobile,address,createdAt)
VALUES ("Bui Quang Vinh",0837762135,"Huế",now());
INSERT INTO orders(fullname,mobile,address,createdAt)
VALUES ("TR VAN PHON",0837762231,"Huế",now());


CREATE TABLE orderItem(
	id INT  AUTO_INCREMENT PRIMARY KEY,
    price DECIMAL(12,0),
    quantity INT NOT NULL,
    orderID INT,
    productID INT,
    productTitle  VARCHAR(255) NOT NULL
); 

drop table orderItem;

INSERT INTO orderItem(id,price, quantity , orderID, productID, productTitle)
VALUES (1,233230,5,1,2,"Bánhh");

INSERT INTO orderItem(id,price, quantity , orderID, productID, productTitle)
VALUES (2,222300,10,2,1,"Kẹo");









