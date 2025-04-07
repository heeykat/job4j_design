create table cat_owner(
    id serial primary key,
    name varchar(255)
);

create table cats(
    id serial primary key,
    name varchar(255),
    cat_owner_id int references cat_owner(id)
);

insert into cat_owner(name) values ('Katya Savchuk');
insert into cats(name, cat_owner_id) VALUES ('Bulochka', 1), ('Kotleta', 1);

select * from cats;

select * from cat_owner where id in (select cat_owner_id from cats);