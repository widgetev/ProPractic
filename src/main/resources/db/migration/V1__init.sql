CREATE TABLE users (id bigserial PRIMARY KEY, username varchar(255) unique);
insert into users (username) values ('Vasya'),('MAsha'),('shdfwvn345ofvm');

CREATE TABLE products (id bigserial PRIMARY KEY, accnum varchar(25) unique, sum money, type varchar(4), userid bigint);
INSERT INTO "products" ("id", "accnum", "sum", "type", "userid") VALUES
                                                                     (1,	'40817810100000100000',	'$0.00',	'ACC',	2),
                                                                     (2,	'2201000000000000',	'$999,999.90',	'CARD',	0),
                                                                     (3,	'40817810100000100001',	'$0.00',	'ACC',	2),
                                                                     (4,	'2201000000000001',	'$999,999.90',	'CARD',	1),
                                                                     (5,	'40817810100000100002',	'$0.00',	'ACC',	2),
                                                                     (6,	'2201000000000002',	'$999,999.90',	'CARD',	2),
                                                                     (7,	'2201000000000003 ',	'$999,999.90',	'CARD',	NULL),
                                                                     (8,	'2201000000000004 ',	'$999,999.90',	'CARD',	NULL);
