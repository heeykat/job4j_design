CREATE TABLE teens (
    name VARCHAR(100),
    gender CHAR(1) 
);

INSERT INTO teens (name, gender) VALUES
('Vasya', 'M'),
('Petya', 'M'),
('Kolya', 'M'),
('Masha', 'W'),
('Anya', 'W'),
('Olya', 'W');

SELECT t1.name, t1.gender, t2.name, t2.gender
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender AND t1.name > t2.name;