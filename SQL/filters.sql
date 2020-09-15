create table product(
	id int primary key,
	name varchar(100),
	type_id int,
	expired_date timestamp,
	price int);

create table type(
	id int primary key,
	name varchar(100));
	
insert into product(id, name, type_id, expired_date, price) 
	values(1, 'fresh apple', 1, '2020-12-30 12:00:00', 100);
insert into product(id, name, type_id, expired_date, price) 
	values(2, 'french cheese', 2, '2020-10-20 12:00:00', 200);
insert into product(id, name, type_id, expired_date, price) 
	values(3, 'italian cheese', 2, '2020-10-18 12:00:00', 250);
insert into product(id, name, type_id, expired_date, price) 
	values(4, 'vanilla icecream', 3, '2020-10-30 12:00:00', 50);
insert into product(id, name, type_id, expired_date, price) 
	values(5, 'chocolate icecream', 3, '2020-09-25 12:00:00', 60);
insert into product(id, name, type_id, expired_date, price) 
	values(6, 'goats milk', 4, '2020-09-14 12:00:00', 30);

insert into type(id, name) values(1, 'apple');
insert into type(id, name) values(2, 'cheese');
insert into type(id, name) values(3, 'icecream');
insert into type(id, name) values(4, 'milk');


--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product as pr
	inner join type as tp on pr.type_id = tp.id where tp.name='cheese';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as pr
	where pr.name like '%icecream%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as pr
	where pr.expired_date < (select current_date + interval '1 month');

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select tp.name, count(*) from product as pr
	inner join type as tp on pr.type_id = tp.id where tp.name='cheese' group by(tp.name);

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product as pr
	inner join type as tp on pr.type_id = tp.id where tp.name='cheese' or tp.name='milk';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select tp.name, count(pr.name) from product as pr 
	inner join type as tp on pr.type_id = tp.id  
	group by(tp.name) having count(tp.name) < 10;

--8. Вывести все продукты и их тип.
select pr.name, tp.name from product as pr
	inner join type as tp on pr.type_id = tp.id;
