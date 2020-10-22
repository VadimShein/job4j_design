create table meetings (
	id serial primary key,
	name varchar(100)
);

create table users (
	id serial primary key,
	name varchar(100)
);

create table meetings_and_users (
	id serial primary key,
	meeting_id int references meetings(id),
	user_id int references users(id),
	participation boolean
);

insert into meetings(name) values('meeting_1');
insert into meetings(name) values('meeting_2');
insert into meetings(name) values('meeting_3');

insert into users(name) values('user_1');
insert into users(name) values('user_2');
insert into users(name) values('user_3');
insert into users(name) values('user_4');

insert into meetings_and_users(meeting_id, user_id, participation) values(1, 1, true);
insert into meetings_and_users(meeting_id, user_id, participation) values(1, 2, true);
insert into meetings_and_users(meeting_id, user_id, participation) values(1, 3, false);
insert into meetings_and_users(meeting_id, user_id, participation) values(2, 1, true);
insert into meetings_and_users(meeting_id, user_id, participation) values(2, 3, true);

--Нужно написать запрос, который получит список всех заявок и количество подтвердивших участников.
select  m.name, count(*) from meetings as m
	left join meetings_and_users as mu on mu.meeting_id = m.id
	where mu.participation = true group by(m.name);

--Нужно получить все совещания, где не было ни одной заявки на посещения
select m.name from meetings as m
	left join meetings_and_users as mu on mu.meeting_id = m.id
	where not exists(select * from meetings_and_users as mu where m.id = mu.meeting_id)
	group by(m.name);






