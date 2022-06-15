SELECT customers.CustomerName, orders.OrderID
FROM customers
CROSS JOIN orders
where customers.CustomerID = orders.CustomerID;