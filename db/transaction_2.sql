start transaction;
SELECT * FROM products;
commit;

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL REPEATABLE READ;

start transaction;
SELECT * FROM products;
update products set available = TRUE, quantity = 1 where name = 'Noise Cancelling Headphones';
commit;

SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION LEVEL serializable;

start transaction; -- действие 2
select sum(quantity) from products; -- действие 5
update products set quantity = 130 where name = 'Wireless Mouse'; -- действие 6
commit; -- действие 8