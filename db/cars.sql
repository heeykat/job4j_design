CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies ( name) VALUES
('Sedan'),
('Hatchback'),
('SUV'),
('Coupe'); 

INSERT INTO car_engines (name) VALUES
('V6 Petrol'),
('V8 Diesel'),
('Electric'),
('Hybrid'); 

INSERT INTO car_transmissions (name) VALUES
('Manual'),
('Automatic'),
('CVT'),
('Semi-Automatic'); 

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Falcon X', 1, 1, 2),  
('Speedster', 2, 2, 1),
('EcoRide', 3, 3, 3),
('Ghost Car', 1, NULL, 2),
('Invisible Rider', NULL, 3, 1),
('Manual Mystery', 2, 2, NULL),
('Future Concept', NULL, NULL, NULL);

SELECT c.id, c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
FROM cars c
LEFT JOIN car_bodies cb ON cb.id = body_id
LEFT JOIN car_engines ce ON ce.id = engine_id
LEFT JOIN car_transmissions ct ON ct.id = transmission_id;

SELECT cb.id, cb.name, c.id, c.name
FROM car_bodies cb
LEFT JOIN cars c ON cb.id = body_id
WHERE c.id IS NULL;

SELECT ce.id, ce.name, c.id, c.name
FROM car_engines ce
LEFT JOIN cars c ON ce.id = engine_id
WHERE c.id IS NULL;

SELECT ct.id, ct.name, c.id, c.name
FROM car_transmissions ct
LEFT JOIN cars c ON ct.id = transmission_id
WHERE c.id IS NULL;