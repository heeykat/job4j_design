CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Alice', 'Smith', 30, 'USA'),
('Bob', 'Johnson', 25, 'Canada'),
('Carlos', 'Gonzalez', 40, 'Mexico'),
('Diana', 'Ivanova', 28, 'Russia'),
('Emma', 'Müller', 35, 'Germany');

SELECT * 
FROM customers 
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES
(150, 1),
(200, 2),
(300, 1),
(120, 3),
(250, 4),
(220, 2);

SELECT * 
FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);