# wordpress

一个人全栈 **Web** 开发成本最低的方式，主要是前端页面就不用费时费力了，只需要花点钱买个主题后端自己想办法写就可以了。

## nginx config

将 **web.conf** 配置到 **nginx** 的配置文件目录: `cp web.conf /etc/nginx/conf.d/web.conf`

再 `nginx -s reload`

web.conf 文件内容：

```nginx
server {
    listen 80;
    listen [::]:80;
    server_name www.emacsvi.com emacsvi.com;

    # 将 HTTP 请求重定向至 HTTPS
    # 第一种 把http的域名请求转成https
    # 如果 Nginx 强制跳转使用的是 301, 浏览器或 Postman 将 Post 请求跳转后使用 GET 请求导致后端出错, Nginx 调整为 307 即可
    return 307 https://$host$request_uri;

    # 第二种 强制将http的URL重写成https
    # rewrite ^(.*) https://$server_name$1 permanent;
}

server {
    listen              443 ssl http2;
    listen              [::]:443;
    server_name  www.emacsvi.com emacsvi.com;
    charset utf-8;
    ssl_certificate     /root/liw/cert-files/fullchain.cer;
    ssl_certificate_key /root/liw/cert-files/emacsvi.com.key;
    root   /root/liw/wp/wp;

    location / {
	index index.html index.php index.htm index.nginx-debian.html;
	try_files $uri $uri/ /index.php?q=$uri&$args;
	autoindex on;
	autoindex_exact_size off;
	autoindex_localtime on;
    }

    location ~ \.php$ {
        fastcgi_pass unix:/run/php/php-fpm.sock;
	fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
	include fastcgi_params;
    }

    location ~* \.(eot|ttf|woff|woff2)$ {
        add_header Access-Control-Allow-Origin '*';
    }

    location ~ /\.ht {
      deny all;
    }
}
```

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

## houzez Theme

记录一下使用这个主题的时候需要修改和调整的一些地方：

-   users-> profile->midnight, email, password
-   settings -> permlinks -> postname
-   elementor -> general -> post type 开启所有 Features -> Flexbox Container -> Inactive 也不知道为什么建议关闭
-   import Demo5
-   customize -> HomePage Settings -> HomePage and Blog to static page 无须操作
-   install wpsmtp plugin -> Brevo -> Configration
-   "Create A Listing" Page extend "Dashboard Creating List" template
-   Houzez -> Field Name for property details add field.


## icons 下载

[https://fontawesome.com/search](https://fontawesome.com/search)