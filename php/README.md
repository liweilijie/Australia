# wordpress

## 在ubuntu上面安装wordpress nginx

[Installing NGINX, MySQL, PHP and WordPress on Ubuntu](https://medium.com/@djakkone/installing-nginx-mysql-php-and-wordpress-on-ubuntu-2ccf8beba11f)

```bash
sudo apt update
sudo apt install nginx
systemctl status nginx
curl http://127.0.0.1/
sudo apt install -y php-fpm php-mysql php-curl php-mbstring php-imagick php-xml php-zip
# 根据php版本号安装gd
sudo apt install -y php8.1-gd
systemctl status php8.1-fpm.service

# 配置nginx
cd /etc/nginx/sites-available
# 备份
sudo cp default default.bak
```

改这一行增加"index.php"

```nginx
# Add index.php to the list if you are using PHP
	index index.php index.html index.htm index.nginx-debian.html;
```

改这一行try_files增加php

```nginx
location / {
		# First attempt to serve request as file, then
		# as directory, then fall back to displaying a 404.
		# try_files $uri $uri/ =404;
		try_files $uri $uri/ /index.php$is_args$args;
	}
```

改这一行开始反向代理, socket的路径用systemctl status php-fpm就能看出来

```nginx
location ~ \.php$ {
		include snippets/fastcgi-php.conf;

	#	# With php-fpm (or other unix sockets):
	#	fastcgi_pass unix:/run/php/php7.4-fpm.sock;
		fastcgi_pass unix:/run/php/php-fpm.sock;
	#	# With php-cgi (or other tcp sockets):
	#	fastcgi_pass 127.0.0.1:9000;
	}
```

改完测试一下有没有错：

```bash
sudo nginx -t
sudo systemctl reload nginx
curl http://localhost
```

安装wordpress:

下载[https://wordpress.org/download/releases/](https://wordpress.org/download/releases/)

解压并且复制到php的目录，并且将权限改成php的权限：

```bash
tar xvf wordpress-6.6.2.tar.gz
sudo mv wordpress/* /var/www/html
sudo chown -R www-data:www-data /var/www/html
ls -l /var/www/html
```

sudo vi /etc/php/8.1/fpm/php.ini

```ini
file_uploads = On
max_execution_time = 600
memory_limit = 512M
post_max_size = 200M
max_input_time = 60
max_input_vars = 4440
upload_max_filesize = 200M
```

接下来可以打开网页安装了。


Increase WordPress memory limit - Current memory limit: 40 MB | We recommend setting memory to at least 128MB -

You have to like this way: Edit this wp-config.php file

```php
define( 'WP_DEBUG', true );

/* That's all, stop editing! Happy publishing. */
define( 'WP_MEMORY_LIMIT', '256M' );
```

download envato market plugin: [envato market](https://www.envato.com/lp/market-plugin/)

nginx 出现413 Request Entity Too Large问题的解决方法



使用php上传图片（大小1.9M)，出现 nginx: 413 Request Entity Too Large 错误。

根据经验是服务器限制了上传文件的大小，但php默认的文件上传是2M，应该不会出现问题。



打开php.ini，把 upload_max_filesize 和 post_max_size 修改为20M，然后重启。

再次上传，问题依旧，可以排除php方面的问题。



原来nginx默认上传文件的大小是1M，可nginx的设置中修改。



解决方法如下：

1.打开nginx配置文件 nginx.conf, 路径一般是：/etc/nginx/nginx.conf。

2.在http{}段中加入 client_max_body_size 20m; 20m为允许最大上传的大小。

3.保存后重启nginx，问题解决。
