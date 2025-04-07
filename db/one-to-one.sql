create table cat_passport(
    id serial primary key,
    number int
);

create table cats(
    id serial primary key,
    name varchar(255),
    passport_id int references cat_passport(id) unique
);

insert into cat_passport(number) values (1001), (1002);
insert into cats(name, passport_id) values ('Bulochka', 1), ('Kotleta', 2);

select * from cats;
select * from cat_passport;
