# wordpress

WordPress是由三大部分组成的，即WordPress核心、主题和插件

- 核心：字面意思，其框架核心代码；
- 主题：决定了网站的外观、设计、界面，基本上理解为访客所看到的网站的样子；
- 插件：扩展WordPress核心的各种功能，达到自己网站的功能定制。

WordPress开发，指的是主题和插件的定制开发。

## plugin development

- 创建插件
- 认识钩子和使用钩子
- 过滤器的使用
- 后台整合创建菜单和子菜单
- 创建小工具
- 创建元数据框
- 自定义插件界面
- 保存插件设置到数据库
- 使用wd提供的api将数据保存到option表里面
- 为自己的插件创建类
- 将选项添加到现在有页面上
- 调用ajax对数据进行请求
- 多语言设置
- 插件权限管制
- 短标签
- 扩展自定义文章类型

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

参考官方图片

## Wpresidence 使用

- map 显示与搜索
- agent经纪人的功能
- 收藏功能
- 注册和登录
- 支持社交帐户登录功能
- compare for properties
- sort codes
- Elementor
- hubspots 留言交流功能 

