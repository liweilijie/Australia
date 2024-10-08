# wordpress

`php`,`wordpress`,`nginx`,`thinkphp` 等框架的使用。

```bash
brew services list
brew services run formula|--all # 启动服务(仅启动不注册)
brew services start formula|--all # 启动服务，并注册开机启动
brew services stop formula|--all # 停止服务，并取消注册
brew services restart formula|--all # 重启服务，并注册
brew services cleanup # 消除不用的无用的配置
```
此处注册开机自启后，会自动创建 .plist 文件，取消开机自启会自动删去该文件

```bash
# 然后关闭服务，并禁用开机自启动
launchctl unload -w ~/Library/LaunchAgents/homebrew.mxcl.zookeeper.plist
launchctl unload -w ~/Library/LaunchAgents/homebrew.mxcl.kafka.plist
```

## mac install nginx php@7.4

mac install dnsmasq:

```bash
brew install bash-completion
brew install nginx

brew install dnsmasq
vi /usr/local/etc/dnsmasq.conf
# 内容如下,最后没有换行:
address=/.dev/127.0.0.1
listen-address=127.0.0.1


# To start dnsmasq now and restart at startup:
  sudo brew services start dnsmasq
```

现在你已经有了一个可以工作的 DNS 服务器，你可以在自己的操作系统上配置来使用它。有使用两种方法：

发送所有 DNS 请求到 Dnsmasq
只发送 .devel 的请求到 Dnsmasq
第一种方法非常简单，只要在系统偏好中改变你的 DNS 设置——但是可能在 Dnsmasq 配置文件不添加额外的修改的时候并不会生效。

第二种方法显得有点微妙，但并没有非常。大多数类 Unix 的操作系统有叫做 /etc/resolv.conf 的配置文件，用以控制 DNS 查询的执行方式，包括用于 DNS 查询的默认服务器（这是连接到网络或者在系统偏好中修改 DNS 服务器时自动设置的）。

macOS 也允许你通过在 /etc/resolver 文件夹中创建新的配置文件来配置额外的解析器。这个目录可能还不存在于你的系统中，所以你的第一步应该是创建它：

sudo mkdir /etc/resolver
在此目录创建devel文件，并写人nameserver 127.0.0.1

在这里，devel 是我配置 Dnsmasq 来响应的顶级域名，127.0.0.1 是要使用的服务器的 IP 地址。

一旦你创建了这个文件，macOS 将会自动读取并完成。 ps: 目前现在只发现配置在/etc/resolver下可以，没搞懂配置在/etc/resolv.conf为什么没生效？

至此，你ping任何以.devel结尾的域名就会解析到本地，无论地址是否存在


```bash
Docroot is: /usr/local/var/www

The default port has been set in /usr/local/etc/nginx/nginx.conf to 8080 so that
nginx can run without sudo.

nginx will load all files in /usr/local/etc/nginx/servers/.

To start nginx now and restart at login:
  brew services start nginx
Or, if you don't want/need a background service you can just run:
  /usr/local/opt/nginx/bin/nginx -g daemon\ off\;
```

### 安装nginx和php

[mac install dnsmasq php nginx](https://gist.github.com/dtomasi/ab76d14338db82ec24a1fc137caff75b)

```bash
brew tap shivammathur/php
brew install shivammathur/php/php@7.4
brew link --force --overwrite php@7.4
brew services start php@7.4
echo 'export PATH="/opt/homebrew/opt/php@7.4/bin:$PATH"' >> ~/.zshrc # or ~/.bashrc
echo 'export PATH="/opt/homebrew/opt/php@7.4/sbin:$PATH"' >> ~/.zshrc # or ~/.bashrc

# 查看占用端口
sudo lsof -i -n -P | grep php-fpm
lsof -Pni4 | grep LISTEN | grep php
```

## 在 ubuntu 上面安装 wordpress nginx

nginx log: /usr/local/var/log/nginx/
```bash
nginx
nginx -s reload
nginx -s quit
```

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
# memory_limit = 2G
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
