CREATE DATABASE java_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    
    
create table roles (
	id int primary key,
	role varchar(100)
);

create table users (
	id int primary key,
	name varchar(100),
	role_id int references roles(id)
);

create table rules (
	id int primary key,
	rule varchar(100)
);

create table roles_and_rules (
	id int primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table categories (
	id int primary key,
	category varchar(100)
);

create table states (
	id int primary key,
	state varchar(100)
);

create table items (
	id int primary key,
	item varchar(100),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

create table comments (
	id int primary key,
	comment text,
	item_id int references items(id)
);

create table attachs (
	id int primary key,
	attach text,
	item_id int references items(id)
);



insert into roles(id, role) values(1,'java programmer');
insert into roles(id, role) values(2,'php programmer');
insert into roles(id, role) values(3,'administrator');

insert into users(id, name, role_id) values(1, 'Vasya', 1);
insert into users(id, name, role_id) values(2, 'Petya', 2);
insert into users(id, name, role_id) values(3, 'Masha', 3);

insert into rules(id, rule) values(1, 'work hard');
insert into rules(id, rule) values(2, 'sleep');

insert into rules_and_roles(id, role_id, rule_id) values(1, 1, 1);
insert into rules_and_roles(id, role_id, rule_id) values(2, 2, 1);
insert into rules_and_roles(id, role_id, rule_id) values(3, 3, 2);

insert into categories(id, category) values(1, 'staffer');
insert into categories(id, category) values(2, 'student');
insert into categories(id, category) values(3, 'freelancer');

insert into states(id, state) values(1, 'works');
insert into states(id, state) values(2, 'on holiday');

insert into items(id, item, user_id, category_id, state_id) values(1,'first item', 1, 1, 1);
insert into items(id, item, user_id, category_id, state_id) values(2,'second item', 1, 2, 2);
insert into items(id, item, user_id, category_id, state_id) values(3,'third item', 3, 3, 1);

insert into comments(id, comment, item_id) values(1, 'first comment', 1);
insert into comments(id, comment, item_id) values(2, 'second comment', 2);
insert into comments(id, comment, item_id) values(3, 'third comment', 3);

insert into attachs(id, attach, item_id) values(1, 'path to first file', 1);
insert into attachs(id, attach, item_id) values(2, 'path to second file', 2);
insert into attachs(id, attach, item_id) values(3, 'path to third file', 3);

