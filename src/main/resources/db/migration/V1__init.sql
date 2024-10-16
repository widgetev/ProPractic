CREATE TABLE users (id bigserial PRIMARY KEY, username varchar(255) unique);
insert into users (username) values ('Vasya'),('MAsha'),('shdfwvn345ofvm');

CREATE TABLE products (id bigserial PRIMARY KEY, accnum varchar(25) unique, sum money, type varchar(4), userid bigint);

