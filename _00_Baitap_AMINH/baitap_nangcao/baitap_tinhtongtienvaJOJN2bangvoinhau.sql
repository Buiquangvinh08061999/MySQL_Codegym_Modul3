SELECT * FROM banking_transaction_management.transfers;

USE banking_transaction_management;

SELECT  TF.sender_id AS 'Sender ID',
		CT.full_name AS 'Sender name',
		SUM(TF.transaction_amount) AS 'Total amount',
		MONTH(TF.created_at) AS 'Month',
        YEAR(TF.created_at) AS 'Year',
		CASE
			WHEN SUM(TF.transaction_amount) >=1000 THEN "Good"
            ELSE "Bad"
        END AS Rate
FROM transfers AS TF
INNER JOIN customers AS CT
ON  TF.sender_id = CT.id
WHERE YEAR(TF.created_at) = 2021
GROUP BY CT.full_name
ORDER BY TF.sender_id
