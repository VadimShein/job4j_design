CREATE TABLE company(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'company_a');
insert into company(id, name) values(2, 'company_b');
insert into company(id, name) values(3, 'company_c');
insert into company(id, name) values(4, 'company_d');
insert into company(id, name) values(5, 'company_e');
insert into company(id, name) values(6, 'company_f');


insert into person(id, name, company_id) values(1, 'person_1_a', 1);
insert into person(id, name, company_id) values(2, 'person_2_a', 1);
insert into person(id, name, company_id) values(3, 'person_3_a', 1);
insert into person(id, name, company_id) values(4, 'person_4_a', 1);
insert into person(id, name, company_id) values(5, 'person_5_a', 1);

insert into person(id, name, company_id) values(6, 'person_1_b', 2);
insert into person(id, name, company_id) values(7, 'person_2_b', 2);
insert into person(id, name, company_id) values(8, 'person_3_b', 2);

insert into person(id, name, company_id) values(9, 'person_1_c', 3);
insert into person(id, name, company_id) values(10, 'person_2_c', 3);
insert into person(id, name, company_id) values(11, 'person_3_c', 3);
insert into person(id, name, company_id) values(12, 'person_4_c', 3);


insert into person(id, name, company_id) values(13, 'person_1_d', 4);
insert into person(id, name, company_id) values(14, 'person_2_d', 4);

insert into person(id, name, company_id) values(15, 'person_1_e', 5);
insert into person(id, name, company_id) values(16, 'person_2_e', 5);
insert into person(id, name, company_id) values(17, 'person_3_e', 5);
insert into person(id, name, company_id) values(18, 'person_4_e', 5);

insert into person(id, name, company_id) values(19, 'person_1_f', 6);

-- имена людей, не состоящих в компании с id = 5
select p.name, c.name from person as p
	inner join company as c
	on (p.company_id) = c.id
	where c.id !=5;

-- компания с наибольшим количеством человек + количество
select c.name, count(*) from person as p
	inner join company as c
	on c.id = p.company_id
	where c.id = p.company_id
	group by(c.name) order by c.count desc limit 1;


