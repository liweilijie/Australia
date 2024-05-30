drop table if exists `test`;
create table `test` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `password` varchar(50) comment 'password',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='just for test';

insert into `test`(id, name, password) values (1, 'test', '123456');
insert into `test`(id, name, password) values (2, 'Australia', 'merben');

drop table if exists `demo`;
create table `demo` (
  `id` bigint not null comment 'id',
  `name` varchar(50) comment 'name',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='just for demo test';

insert into `demo`(id, name) values (1, 'demo');
insert into `demo`(id, name) values (2, 'Australia');