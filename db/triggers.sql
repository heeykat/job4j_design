CREATE TABLE products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

CREATE OR REPLACE FUNCTION tax()
RETURNS TRIGGER AS
$$
BEGIN
    UPDATE products
    SET price = price * 1.2 
    WHERE id IN (SELECT id FROM new_rows);  
    RETURN NULL;  
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tax_trigger_statement
AFTER INSERT ON products
REFERENCING NEW TABLE AS new_rows  
FOR EACH STATEMENT              
EXECUTE FUNCTION tax();

INSERT INTO products (name, producer, count, price)
VALUES 
    ('product_1', 'producer_1', 3, 50),
    ('product_2', 'producer_2', 5, 100);

SELECT * FROM products;

DROP TRIGGER tax_trigger_statement on products;

CREATE OR REPLACE FUNCTION tax1()
RETURNS TRIGGER AS
$$
BEGIN
    NEW.price := NEW.price * 1.2;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tax_trigger_row
BEFORE INSERT
ON products
FOR EACH ROW
EXECUTE FUNCTION tax1();

INSERT INTO products (name, producer, count, price)
VALUES 
    ('product_3', 'producer_3', 8, 70),
    ('product_4', 'producer_4', 10, 110);

SELECT * FROM products;

CREATE TABLE history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

CREATE OR REPLACE FUNCTION log_price_history()
RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_log_price_after_insert
AFTER INSERT
ON products
FOR EACH ROW
EXECUTE FUNCTION log_price_history();

INSERT INTO products (name, producer, count, price)
VALUES 
    ('product_5', 'producer_5', 8, 80),
    ('product_6', 'producer_6', 10, 120);

SELECT * FROM products;
SELECT * FROM history_of_price;