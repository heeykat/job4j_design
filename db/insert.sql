insert into roles(name) 
values 
	('role 1'), 
	('role 2'), 
	('role 3');
	
insert into rules(name) 
values 
	('rule 1'), 
	('rule 2'), 
	('rule 3');
	
insert into users(name, role_id) 
values 
	('Ivan', 1), 
	('Petr', 1), 
	('Slava', 2);
	
insert into roles_rules(role_id, rules_id) 
values 
	(1, 1), 
	(1, 2), 
	(2, 1);
	
insert into states(name) 
values 
	('state 1'), 
	('state 2'), 
	('state 3');
	
insert into categories(name) 
values 
	('category 1'), 
	('category 2'), 
	('category 3');

insert into items(name, user_id, state_id, category_id)	
values
	('phone', 2, 1, 3),
	('laptop', 1, 1, 1),
	('headphones', 2, 2, 2);

insert into comments (name, item_id) 
values
	('comment 1', 1),
	('comment 2', 3),
	('comment 3', 3);

insert into attachs (name, item_id) 
values
	('attachs 1', 2),
	('attachs 2', 2),
	('attachs 3', 1);