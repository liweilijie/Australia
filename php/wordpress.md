# wordpress

## mac 最佳开发环境

如果生成一个 **wordpress** 项目之后，最好我们用域名的方式进行访问
那么我们就可以在本地开发和发布环境一致都是域名访问

那么通过两部就可以完成

-   修改 **/etc/hosts** 文件，使用域名指向 增加一个

```text
127.0.0.1 emacsvi.com
127.0.0.1 www.emacsvi.com
127.0.0.1 china.com
127.0.0.1 www.china.com
```

-   配置 **nginx** 指向对应的 php-fpm 的端口
<!-- -   同步 options 表中的 siteurl,home 字段 -->

## wordpress 目录结构
