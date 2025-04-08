create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date)
VALUES
    ('Rainbow Fish', 2500, '1758-01-01'),
    ('Golden Fish', 800, NULL),
    ('Deep Sea Anglerfish', 12000, '1837-11-15'),
    ('Clown Fish', 1500, '1856-05-22'),
    ('Ancient Coelacanth', 15000, '1938-12-23'),
    ('Silver Shark', 9500, '1822-07-08'),
    ('Fire Coral', 50000, NULL),
    ('Blue Whale', 11000, '1693-04-17'),
    ('Immortal Jellyfish', NULL, '1883-09-01'),
    ('Stone Fish', 3500, '1799-03-30'),
    ('Mountain Lion', 2500, '1776-10-12'),
    ('Electric Eel', 2800, '1766-02-28'),
    ('Abyssal Gigantfish', 32000, NULL),
    ('Red Snapper', 1200, '1802-06-19'),
    ('Black Dragonfish', 18000, '1901-08-11'),
    ('Forest Deer', 800, NULL),
    ('Time-Tardigrade', 55000, '1972-07-04'),
    ('Glowing Squid', 9500, '1854-01-27'),
    ('Desert Scorpion', 1500, '1820-09-15'),
    ('Eternal Sponge', 42000, NULL);

select * from fauna;
select * from fauna where name ~* 'fish'; 
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';