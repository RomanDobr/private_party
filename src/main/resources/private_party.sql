--create database private_party
--1. Создать таблицу с гостями: name, email, phone (id само собой)
create table guest (id serial primary key,
					name varchar(100) NOT NULL,
				    email varchar(100) NOT NULL UNIQUE,
				   phone varchar(100));

--2. Создать пользователя manager. Он может заносить данные в таблицу с гостями, а так же смотреть список гостей.
create user manager with password '1234';
grant select, update, insert on guest to manager;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO manager;

--3. Создать view guest_name. Должны быть только имена гостей.
create view guest_name as (select name from guest);

--4. Создать пользователя guard. Он может только смотреть только guest_name.
create user guard with password '1234';
grant select on guest_name to guard;