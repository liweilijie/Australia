# 配置站点

- dns域名解析，使用本地 hosts 文件 `127.0.0.1 melbourne.com`
- http服务器(nginx)上的访问位置： 网站文件夹所在的位置, 最后重启nginx服务 `nginx -s reload`

**vi /usr/local/etc/nginx/servers/dev.conf**

```nginx.conf
server {
  listen 80;
  server_name melbourne.com;
  root /Users/liwei/Desktop/Australia/php/basic;

  index index.php index.html index.htm;
  client_max_body_size 1G;

  location / {
    try_files $uri $uri/ /index.php?$args;
  }

  location ~ \.php$ {
    include        fastcgi_params;
    fastcgi_pass   127.0.0.1:9000;
    fastcgi_index  index.php;
    fastcgi_param  SCRIPT_FILENAME    $document_root$fastcgi_script_name;

  }

  access_log /usr/local/var/log/nginx/phpdev.log;
  error_log /usr/local/var/log/nginx/phpdev.error.log;
}
```
