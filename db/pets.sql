CREATE TABLE pets_owners (
    id serial PRIMARY KEY,
    name VARCHAR(50),
    country VARCHAR(50)
);

CREATE TABLE pets (
    id serial PRIMARY KEY,
    name VARCHAR(50),
    colour VARCHAR(30),
	owner_id int references pets_owners(id)
);

insert into pets_owners(name, country) values ('Katya', 'Slovenia');
insert into pets_owners(name, country) values ('Vika', 'Armenia');
insert into pets_owners(name, country) values ('Anya', 'Germany');
insert into pets_owners(name, country) values ('Olya', 'Portugal');
insert into pets_owners(name, country) values ('Anya', 'Hungary');
insert into pets_owners(name, country) values ('Love', 'Armenia');
insert into pets_owners(name, country) values ('Sonya', 'Armenia');
insert into pets_owners(name, country) values ('Yulya', 'Armenia');

insert into pets(name, colour, owner_id) values ('Bulochka', 'tabby', 1);
insert into pets(name, colour, owner_id) values ('Kotleta', 'tabby', 1);
insert into pets(name, colour, owner_id) values ('Hash', 'tabby', 2);
insert into pets(name, colour, owner_id) values ('Velik', 'white with spots', 2);
insert into pets(name, colour, owner_id) values ('Koshka', 'tabby with white parts', 3);
insert into pets(name, colour, owner_id) values ('Koshka', 'black', 3);
insert into pets(name, colour, owner_id) values ('Iriska', 'ginger', 4);
insert into pets(name, colour, owner_id) values ('Diva', 'gold', 5);
insert into pets(name, colour, owner_id) values ('Nadya', 'tabby', 6);
insert into pets(name, colour, owner_id) values ('Beyba', 'tabby', 7);
insert into pets(name, colour, owner_id) values ('Ciri', 'white with spots', 7);
insert into pets(name, colour, owner_id) values ('Sary', 'white', 8);
insert into pets(name, colour, owner_id) values ('Semyon', 'ginger', 8);
insert into pets(name, colour, owner_id) values ('Seryozha', 'gray', 8);

select pets_owners.id, pets_owners.name, country, pets.name, colour
from pets_owners
join pets on pets_owners.id = owner_id;

select po.id, po.name, country, p.name, colour
from pets_owners po
join pets p on po.id = owner_id;

select po.id, po.name as "owner's name", country, p.name as "pet's name", colour as "pet's color"
from pets_owners po
join pets p on po.id = owner_id;

select po.id, po.name "owner's name", country, p.name "pet's name", colour "pet's color"
from pets_owners po
join pets p on po.id = owner_id;

TRUNCATE TABLE pets RESTART IDENTITY CASCADE;

