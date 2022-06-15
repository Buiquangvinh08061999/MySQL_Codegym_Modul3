SELECT customers.CustomerName, orders.OrderID 
FROM customers
left join orders ON customers.CustomerID = orders.CustomerID
order by customers.CustomerName;