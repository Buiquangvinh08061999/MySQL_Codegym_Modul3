SELECT customers.CustomerName, orders.OrderID
FROM customers
right JOIN orders ON customers.CustomerID = orders.CustomerID
order by customers.CustomerName;