select * from pets;

start transaction;
savepoint savepoint_1;
insert into pets (name, colour) values ('name_1', 'colour_1');
savepoint savepoint_2;
delete from pets;
drop table pets;
rollback to savepoint_2;
rollback to savepoint_1;
commit transaction;

start transaction;
insert into pets (name, colour) values ('name_1', 'colour_1');
delete from pets;
drop table pets;
rollback transaction;