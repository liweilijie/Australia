create database mybatis;
use mybatis;
drop table if exists tb_user;
create table tb_user (
    id int primary key auto_increment,
    username varchar(64),
    password varchar(64),
    gender char(1),
    addr varchar(255)
);

INSERT INTO tb_user VALUES (1, 'zhangsan', '123', '男', '北京');
INSERT INTO tb_user VALUES (2, '李四', '234', '女', '河北');
INSERT INTO tb_user VALUES (3, '王五', '111', '男', '成都');
