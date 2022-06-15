create database practicejoin;
drop database practicejoin;

use practicejoin; 
CREATE TABLE customers(
 CustomerID INT,
 CustomerName NVARCHAR(100),
 ContactName NVARCHAR(100),
 Address NVARCHAR(100),
 City VARCHAR(100),
 PostalCode VARCHAR(100),
 Country VARCHAR(100)
);
CREATE TABLE Orders(
 OrderID INT,
 CustomerID INT,
 EmployeeID INT,
 OrderDate VARCHAR(100) ,
 ShipperID INT
);

