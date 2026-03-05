-- Create database
CREATE DATABASE audit_system;
USE audit_system;

-- Main table
CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2)
);

-- Log table for recording changes
CREATE TABLE Employee_Log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT,
    action_type VARCHAR(20),
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trigger for INSERT
DELIMITER $$

CREATE TRIGGER after_employee_insert
AFTER INSERT ON Employees
FOR EACH ROW
BEGIN
    INSERT INTO Employee_Log(emp_id, action_type)
    VALUES (NEW.emp_id, 'INSERT');
END $$

DELIMITER ;

-- Trigger for UPDATE
DELIMITER $$

CREATE TRIGGER after_employee_update
AFTER UPDATE ON Employees
FOR EACH ROW
BEGIN
    INSERT INTO Employee_Log(emp_id, action_type)
    VALUES (NEW.emp_id, 'UPDATE');
END $$

DELIMITER ;

-- Insert sample data
INSERT INTO Employees VALUES
(1, 'Ganesh', 'IT', 50000),
(2, 'Ravi', 'HR', 45000);

-- Update example
UPDATE Employees
SET salary = 52000
WHERE emp_id = 1;

-- View for daily activity report
CREATE VIEW Daily_Activity_Report AS
SELECT 
    emp_id,
    action_type,
    DATE(action_time) AS activity_date,
    COUNT(*) AS total_actions
FROM Employee_Log
GROUP BY emp_id, action_type, DATE(action_time);

-- View the report
SELECT * FROM Daily_Activity_Report;
