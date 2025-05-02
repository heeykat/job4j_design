CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name TEXT,
    quantity INTEGER,
    available BOOLEAN
);

INSERT INTO products (name, quantity, available) VALUES
('Wireless Mouse', 120, TRUE),
('Gaming Keyboard', 45, TRUE),
('USB-C Charger', 0, FALSE);

start transaction;
SELECT * FROM products;
insert into products (name, quantity, available) VALUES ('Noise Cancelling Headphones', 2, TRUE);
commit transaction;

start transaction;
update products set available = TRUE, quantity = 1 where name = 'USB-C Charger';
commit transaction;

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL REPEATABLE READ;

start transaction;
SELECT * FROM products;
update products set available = FALSE, quantity = 0 where name = 'Noise Cancelling Headphones';
commit;

start transaction;
SELECT * FROM products;
update products set available = TRUE, quantity = 6 where name = 'Noise Cancelling Headphones';
rollback;

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL serializable;

start transaction; -- действие 1
select sum(quantity) from products; -- действие 3
update products set quantity = 56 where name = 'Gaming Keyboard'; -- действие 4
commit; -- действие 7