create table body(
	id serial primary key,
	name varchar(100)
);

create table motor(
	id serial primary key,
	name varchar(100)
);

create table transmission(
	id serial primary key,
	name varchar(100)
);

create table car(
	id serial primary key,
	name varchar(100),
	body_id int references body(id),
	motor_id int references motor(id),
	transmission_id int references transmission(id)
);

insert into body(name) values('BMW_body');
insert into body(name) values('Audi_body');
insert into body(name) values('Ford_body');
insert into body(name) values('Lexus_body');

insert into motor(name) values('BMW_motor');
insert into motor(name) values('Audi_motor');
insert into motor(name) values('Ford_motor');
insert into motor(name) values('KIA_motor');

insert into transmission(name) values('BMW_transmission');
insert into transmission(name) values('Audi_transmission');
insert into transmission(name) values('Ford_transmission');
insert into transmission(name) values('Ford_transmission');

insert into car(name, body_id, motor_id, transmission_id) values('BMW', '1', '1', '1');
insert into car(name, body_id, motor_id, transmission_id) values('Audi', '2', '2', '2');
insert into car(name, body_id, motor_id, transmission_id) values('Ford', '3', '3', '3');

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name, m.name, t.name from car as c 
	left outer join body as b on c.body_id=b.id 
	left outer join motor as m on c.motor_id=m.id 
	left outer join transmission as t on c.transmission_id=t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select m.name, b.name, t.name from car as c
	full outer join motor as m on c.motor_id=m.id 
	full outer join body as b on c.body_id=b.id
	full outer join transmission as t on c.transmission_id=t.id
	where c.id is null
	;



