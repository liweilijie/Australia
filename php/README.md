# wordpress

`php`,`wordpress`,`nginx`,`thinkphp` 等框架的使用。

## 在 ubuntu 上面安装 wordpress nginx

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


# install php 7.4
sudo add-apt-repository ppa:ondrej/php
sudo apt update
sudo apt install php7.4-memcache php7.4-imagick php7.4-redis php7.4-bcmath php7.4-intl php7.4-mcrypt php7.4-cgi php7.4-fpm php7.4-mysql php7.4-curl php7.4-gd php7.4-imap php7.4-tidy php7.4-xmlrpc php7.4-xml php7.4-xsl php7.4-mbstring php7.4-zip php7.4-cli php7.4-soap php7.4-gmp php7.4-sqlite3
sudo update-alternatives --config php




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

改这一行 `try_files` 增加 php

```nginx
location / {
		# First attempt to serve request as file, then
		# as directory, then fall back to displaying a 404.
		# try_files $uri $uri/ =404;
		try_files $uri $uri/ /index.php$is_args$args;
	}
```

改这一行开始反向代理, **unix socket**的路径用`systemctl status php8.1-fpm.service`就能看出来

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

**安装 wordpress**:

下载[https://wordpress.org/download/releases/](https://wordpress.org/download/releases/)

解压并且复制到 php 的目录，并且将权限改成 php 的权限：

```bash
tar xvf wordpress-6.6.2.tar.gz
sudo mv wordpress/* /var/www/html
sudo chown -R www-data:www-data /var/www/html
ls -l /var/www/html
```

修改 `php.ini` 的一些配置信息，主要增加 **php** 的内存，执行时间，上传文件大小等参数后续安装插件和主题需要上传文件和执行时间较长的需求。

`sudo vi /etc/php/8.1/fpm/php.ini`

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

**注意事项**：

`Increase WordPress memory limit - Current memory limit: 40 MB | We recommend setting memory to at least 128MB -` 报这个错误解决方法：

You have to like this way: Edit this `wp-config.php` file

```php
define( 'WP_DEBUG', true );

/* That's all, stop editing! Happy publishing. */
define( 'WP_MEMORY_LIMIT', '256M' );
```

`envato market下载不了手动到官网下载并且安装`：

download envato market plugin: [envato market](https://www.envato.com/lp/market-plugin/)

`nginx 出现 413 Request Entity Too Large 问题的解决方法`:

使用 php 上传图片(大小 1.9M)，出现 nginx: **413 Request Entity Too Large** 错误。

根据经验是服务器限制了上传文件的大小，但 php 默认的文件上传是 2M，应该不会出现问题。

打开 php.ini，把 `upload_max_filesize` 和 `post_max_size` 修改为 **200M**，然后重启。

再次上传，问题依旧，可以排除 php 方面的问题。

原来**nginx**默认上传文件的大小是 1M，可 nginx 的设置中修改。

解决方法如下：

    1. 打开**nginx**配置文件`nginx.conf`, 路径一般是：`/etc/nginx/nginx.conf`。
    2. 在`http{}`段中加入`client_max_body_size 200m;` 200m 为允许最大上传的大小。
    3. 保存后重启**nginx**，问题解决。

## reference

-   [wpresidence server requirements](https://help.wpresidence.net/article/theme-wordpress-server-requirements/)
-   [woocommerce server recommendations](https://woocommerce.com/document/server-requirements/)
