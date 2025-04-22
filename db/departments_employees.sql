CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_id INTEGER REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES
('IT'),
('HR'),
('Finance'),
('Marketing'),
('Operations'),  
('Legal');      

INSERT INTO employees (name, department_id) VALUES
('John Smith', 1),
('Emily Johnson', 1),
('Michael Brown', 2),
('Sarah Davis', 2),
('Robert Wilson', 3),
('Jennifer Miller', 3),
('David Taylor', 4),
('Jessica Anderson', 4),
('Alex Chen', NULL),
('Maria Garcia', NULL),
('James Wilson', NULL),
('Sophia Lee', NULL);

-- запросы с left, right, full, cross соединениями
SELECT * 
FROM departments d
JOIN employees e ON  d.id = department_id;

SELECT * 
FROM departments d
LEFT JOIN employees e ON  d.id = department_id;

SELECT * 
FROM departments d
RIGHT JOIN employees e ON  d.id = department_id;

SELECT * 
FROM departments d
CROSS JOIN employees e;

-- департаменты, у которых нет работников
SELECT * 
FROM departments d
LEFT JOIN employees e ON  d.id = department_id
WHERE e.id iS NULL;

-- запросы с одинаковыми результатами
SELECT * 
FROM departments d
RIGHT JOIN employees e ON  d.id = department_id;

SELECT * 
FROM employees e
LEFT JOIN departments d ON  d.id = department_id;