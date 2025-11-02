CREATE DATABASE company;
USE company;

CREATE TABLE employee (
  EmpID INT PRIMARY KEY,
  Name VARCHAR(50),
  Salary DOUBLE
);

INSERT INTO employee VALUES
(101, 'Alice', 60000),
(102, 'Bob', 55000),
(103, 'Charlie', 70000);