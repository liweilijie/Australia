# wordpress

一个人全栈 Web 开发成本最低的方式，主要是前端页面就不用费时费力了，只需要花点钱买个主题后端自己想办法写就可以了。

## reset wordpress

创建数据库:

```bash
# 开发用的一个数据库
create database wpdb default character set utf8mb4 collate utf8mb4_general_ci;
CREATE USER 'liweidev'@'%' IDENTIFIED BY 'E..9';
GRANT ALL ON dev.* TO 'liweidev'@'%';
```

重置数据库及主题:

```bash
# revoke Theme Lisence
xxx.com/wp-admin to revoke lisence.

# remove the directory
rm -rf wp

# delete database
drop database wpdb;
create database wpdb default character set utf8mb4 collate utf8mb4_general_ci;
flush privileges;
```
