CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    gender VARCHAR(10),
    address TEXT
);

CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    title TEXT
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    client_id INT REFERENCES clients(client_id),
    movie_id INT REFERENCES movies(movie_id)
);

INSERT INTO clients (full_name, gender, address) VALUES
('Ольга Егорова', 'женский', '1-ый Казанский переулок, д. 14'),
('Иванов Сергей', 'мужской', 'ул. Центральная, д. 40, кв. 74'),
('Иванов Сергей', 'мужской', 'ул. Ленина, д. 7, кв. 130');

INSERT INTO movies (title) VALUES
('Пираты Карибского моря'),
('Матрица: Революция'),
('Человек, который изменил всё'),
('Интерстеллар');

INSERT INTO orders (client_id, movie_id) VALUES (1, 1), (1, 2);

INSERT INTO orders (client_id, movie_id) VALUES (2, 3), (2, 4);

INSERT INTO orders (client_id, movie_id) VALUES (3, 2);

SELECT * FROM clients;
SELECT * FROM movies;
SELECT * FROM orders;