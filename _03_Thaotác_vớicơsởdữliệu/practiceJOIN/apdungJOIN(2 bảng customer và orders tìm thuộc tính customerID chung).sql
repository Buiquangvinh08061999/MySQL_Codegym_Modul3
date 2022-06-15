SELECT orders.OrderID, customers.CustomerName, orders.OrderDate 
FROM orders
INNER JOIN customers ON orders.CustomerID = customers.CustomerID;