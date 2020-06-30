create table users (
  id serial primary key,
  name varchar(200),
  password varchar(200)
);
create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  author_id int references users(id),
  created timestamp without time zone not null default now(),
  topic boolean default false,
  topic_post_id int references posts(id)
);
insert into users(name, password) values ('admin', '123456');
insert into users(name, password) values ('user', '123456');
insert into posts(name, description, author_id, topic)
values ('О чём этот форум?', 'Тут будет описание', (select id from users where name = 'admin'), true);