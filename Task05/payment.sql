-- Create database
CREATE DATABASE payment_system;
USE payment_system;

-- Create Accounts table
CREATE TABLE Accounts (
    account_id INT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(20),
    balance DECIMAL(10,2)
);

-- Insert sample accounts (User and Merchant)
INSERT INTO Accounts VALUES
(1, 'Ganesh', 'User', 5000),
(2, 'Amazon Store', 'Merchant', 10000);

-- Check initial balances
SELECT * FROM Accounts;

-- Start transaction for payment
START TRANSACTION;

-- Deduct amount from user account
UPDATE Accounts
SET balance = balance - 1000
WHERE account_id = 1;

-- Add amount to merchant account
UPDATE Accounts
SET balance = balance + 1000
WHERE account_id = 2;

-- If transaction is successful
COMMIT;

-- If there was an error you could use:
-- ROLLBACK;

-- Check final balances after payment
SELECT * FROM Accounts;
