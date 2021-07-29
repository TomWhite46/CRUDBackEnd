drop table if exists word CASCADE;

create table word 
(
	id integer PRIMARY KEY AUTO_INCREMENT, 
	icelandic varchar(255) not null, 
	english varchar(255) not null, 
	pos varchar(255) not null,
	score integer not null
);