create database banking_transaction_management;

use banking_transaction_management;

create table Customers(
	id int(20) not null  AUTO_INCREMENT,
    full_name  varchar(255),
    email varchar(255),
	phone varchar(255),
    address varchar(255),
    balance decimal(12,0),
	created_at datetime  DEFAULT NULL,
	created_by bigint(20) DEFAULT NULL,
	updated_at datetime DEFAULT NULL,
	updated_by bigint(20) DEFAULT NULL,
    deleted tinyint(1) DEFAULT '0',
    primary key(id),
    unique key (email)

);
CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL, 
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `balance` decimal(12,0) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


create table Deposits;

create table Withdraws;

create table Transfers;