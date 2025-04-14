create table product_type 
(
	id serial primary key,
	name varchar(255)
);

create table product
(
    id serial primary key,
    name varchar(255),
	type_id int references product_type(id),
	expiry_date date,
    price float
);

insert into product_type  (name) values
('СЫР'),
('МОЛОКО'),
('МОРОЖЕНОЕ'),
('ЙОГУРТ'),
('ТВОРОГ');

INSERT INTO product (name, type_id, expiry_date, price) VALUES
('Гауда', 1, '2025-06-01', 12.50),
('Чеддер', 1, '2025-07-15', 15.20),
('Бри', 1, '2025-04-05', 18.75),
('Рокфор', 1, '2025-08-10', 25.30),
('Пармезан', 1, '2025-09-05', 25.30),
('Моцарелла', 1, '2025-05-30', 10.90),
('Эдам', 1, '2025-07-22', 11.80),
('Фета', 1, '2025-04-01', 9.50),
('Горгонзола', 1, '2025-08-18', 20.10),
('Сулугуни', 1, '2025-06-15', 14.20),
('Адыгейский', 1, '2025-05-12', 8.90),
('Камамбер', 1, '2025-07-30', 17.60),
('Молоко Домик в деревне 3.2%', 2, '2025-04-09', 2.10),
('Молоко Простоквашино 2.5%', 2, '2025-04-08', 2.30),
('Молоко Веселый молочник 1.5%', 2, '2025-03-20', 1.90),
('Молоко Organic Valley', 2, '2025-06-20', 3.50),
('Мороженое Пломбир ванильное', 3, '2025-08-01', 4.20),
('Мороженое Эскимо шоколадное', 3, '2025-07-15', 3.80),
('Мороженое Стаканчик крем-брюле', 3, '2024-12-20', 2.90),
('Мороженое Фисташковое', 3, '2025-09-10', 5.10),
('Мороженое Ягодный микс', 3, '2025-07-25', 4.50),
('Йогурт Активиа натуральный', 4, '2025-04-05', 1.80),
('Йогурт Danone персик', 4, '2025-03-10', 1.70), 
('Творог Домик в деревне 5%', 5, '2025-04-10', 3.20),
('Творог Простоквашино 9%', 5, '2025-04-14', 3.50),
('Творожная масса с изюмом', 5, '2025-02-20', 2.80);

select p.name 
from product p
join product_type pt on pt.id = type_id
where pt.name = 'СЫР';

select p.name 
from product p
where p.name like 'Мороженое%';

select p.name, expiry_date 
from product p
where expiry_date < CURRENT_DATE;

select p.name, p.price
from product p
join product_type pt on pt.id = type_id
where p.price = (SELECT MAX(price) FROM product);

select pt.name, count(*)
from product p
join product_type pt on pt.id = type_id
group by pt.name;

select p.name 
from product p
join product_type pt on pt.id = type_id
where pt.name in ('СЫР', 'МОЛОКО');

select pt.name, count(*)
from product p
join product_type pt on pt.id = type_id
group by pt.name
having count(*) < 10;

select p.name as product_name, pt.name as type_name
from product p
join product_type pt on pt.id = type_id;