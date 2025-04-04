create table cats(
    id serial primary key,
    name varchar(255),
    colour varchar(255),
    age integer,
    is_female boolean,
    weight float
);

select * from cats;

insert into cats(name, colour, age, is_female, weight) 
values
	('Bulochka', 'tabby', 4, true, 4.5),
	('Kotleta', 'tabby', 3, true, 5.2);

update cats 
set weight = 5.3
where id = 2;

delete from cats;