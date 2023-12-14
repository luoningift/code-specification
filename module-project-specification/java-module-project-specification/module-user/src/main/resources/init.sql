CREATE TABLE `test`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `info` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into test (info) values ('plugin');
insert into test (info) values ('hs');
insert into test (info) values ('trx');
