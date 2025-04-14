create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO devices (name, price) VALUES
('iPhone 15 Pro', 999.99),
('Samsung Galaxy S23', 799.99),
('MacBook Pro 14"', 1999.99),
('Dell XPS 15', 1499.99),
('Sony WH-1000XM5', 349.99),
('Apple Watch Series 9', 399.99),
('iPad Pro 12.9"', 1099.99),
('Bose QuietComfort 45', 329.99),
('PlayStation 5', 499.99),
('Xbox Series X', 499.99),
('Nintendo Switch OLED', 349.99),
('Google Pixel 8 Pro', 899.99),
('Amazon Echo Dot', 49.99),
('Sony Bravia XR 65"', 1999.99),
('Dyson V15 Detect', 699.99),
('RTX 4090 Gaming PC', 2500.00),
('Rolex Submariner', 15000.00),
('Tesla Model S Keycard', 80000.00),
('Surgical Robot', 120000.00),
('Industrial 3D Printer', 7500.00);

INSERT INTO people (name) VALUES
('Ivan Petrov'),
('Maria Sidorova'),
('Aleksey Ivanov'),
('Elena Kuznetsova'),
('Dmitry Smirnov'),
('Olga Vasilyeva'),
('Sergey Popov'),
('Anna Novikova'),
('Mikhail Fedorov'),
('Natalya Morozova'),
('Andrey Volkov'),
('Tatyana Alekseeva'),
('Pavel Lebedev'),
('Yulia Semenova'),
('Artem Kozlov'),
('Elon Musk'),
('Bill Gates'),
('Jeff Bezos'),
('Roman Abramovich');

INSERT INTO devices_people (device_id, people_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10),
(10, 11),
(11, 12),
(12, 13),
(13, 14),
(14, 15),
(15, 1),
(3, 2),
(5, 3),
(7, 5),
(9, 7),
(17, 16),
(19, 16),
(18, 17),
(20, 17),
(16, 18),
(18, 19), 
(16, 19), 
(20, 19); 

select avg(price) from devices;

select p.id, p.name, round(avg(d.price))
from people as p
join devices_people dp on p.id = dp.people_id 
join devices d on d.id = dp.device_id
group by p.id, p.name
having AVG(d.price) > 5000
order by p.id;
