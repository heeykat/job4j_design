create table cats(
    id serial primary key,
    name varchar(255)
);

create table toys(
    id serial primary key,
    name varchar(255)
);

create table cats_toys(
    id serial primary key,
    cat_id int references cats(id),
    toy_id int references toys(id)
);

insert into cats(name) 
values 
	('Bulochka'), 
	('Kotleta');

insert into toys(name) 
values 
	('fishing rod'),
	('tunnel'),
	('rope');

insert into cats_toys(cat_id, toy_id) 
values 
	(1, 1),
	(2, 1),
	(1, 3),
	(2, 2);

select * from cats;
select * from toys;
select * from cats_toys;

