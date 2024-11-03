# plugin development

[wordpress plugin](https://developer.wordpress.org/plugins/)

plugin myself development function.

```bash
brew services run mysql
brew services run php@7.4
brew services run nginx
```

## 为什么要使用 WordPress 的插件

- 无需修改核心代码，不用担心更新版本导致的功能失效。
- 无需担心修改核心代码导致系统崩溃。
- 使用者不需要了解底层的原理，直接安装使用即可。

## 插件加载顺序

插件加载是在模板加载之前就加载好了。

先插件 -> theme/functions.php -> template

## 如何调试和打印输出

```php
echo $variable
exit;

// 或许在函数结尾的时候 使用exit退出, 以便观察函数调用完之后的效果
exit;
```

## 插件的标准格式 

[标准header书写](https://developer.wordpress.org/plugins/plugin-basics/header-requirements/) ：

use file-level PHPDoc DocBlock better.

`* Requires Plugins:  my-plugin, yet-another-plugin` 注意官方的模板里面有这个字段，要删除或者完全修改才能正常使用。不然会去找plugins的依赖树而启动不了。

## 插件的启用，停用 hook, 删除

- activate: `register_activation_hook( __FILE__, 'pluginprefix_function_to_run' );`
- deactivate: `register_deactivation_hook( __FILE__, 'pluginprefix_function_to_run' );`
- delete: create `uninstall.php` to do delete work.
    ```php
    // if uninstall.php is not called by WordPress, die
    if ( ! defined( 'WP_UNINSTALL_PLUGIN' ) ) {
        die;
    }

    $option_name = 'wporg_option';

    delete_option( $option_name );

    // for site options in Multisite
    delete_site_option( $option_name );

    // drop a custom database table
    global $wpdb;
        $wpdb->query( "DROP TABLE IF EXISTS {$wpdb->prefix}mytable" );
    ```
  
## action的挂载与执行

[do_action](https://www.wpdongli.com/reference/functions/add_action/)

挂载: `add_action('wp_footer', 'liw_copyright');` 为`wp_footer`这个钩子挂载方法，可以挂载很多个.

执行: `do_action('wp_footer');` 执行`wp_footer`钩子动作，一切挂载在`wp_footer`钩子上面的动作全部执行一遍

移除：`remove_action('wp_insert_comment', 'comment_insert');` 移除这个钩子上面的某一个方法，或者移除这个钩子上面的所有方法`remove_all_actions('wp_enqueue_scripts');`.

常用action:

## filter

[filter 常用](https://developer.wordpress.org/apis/hooks/filter-reference/)

```php
function liw_filter_fun($value) 
{
    return $value . "liw";
}

function liw_filter_fun_add_time($value)
{
    return date("Y-m-d H:i:s").$value
}
// 一开始我们设置一个需要过滤器处理的变量
$value = "hello";
// 给名为liw_filter的过滤器，挂载一个liw_filter_fun方法，传递给liw_filter的变量都会经过挂载的所有方法进行过滤处理
add_filter("liw_filter", "liw_filter_fun");
add_filter("liw_filter", "liw_filter_fun_add_time");
// 对$value值使用名为liw_filter的过滤器进行过滤，这个时候，由于liw_filter过滤器挂载了liw_filter_fun, add_time方法，使用liw_filter_fun,add_time方法过滤了一次，并且返回给$new_value变量
$new_value = apply_filters("liw_filter", $value);
// 输出新变量
echo $new_value;
exit;
```

常用的过滤器：

- the_content
- content_save_pre
- the_title
- wp_handle_upload_prefilter 上传附件之前处理的，比如改名文件名称，限制文件大小等 [document example](https://developer.wordpress.org/reference/hooks/wp_handle_upload_prefilter/)
- comment_text

## 创建菜单和子菜单

在后台的dashboard左侧栏叫菜单，这里可以创建你想要的任意菜单和每一个页面的功能。


使用插件来创建菜单，在插件中写代码add_action就可以指定位置。

```php
add_action('admin_menu', 'liw_copyright_create_menu');
function liw_copyright_create_menu()
{
    // 创建顶级菜单
    add_menu_page(
        'liwei plugin home',
        'Liweipoo',
        'manage_options',
        'liw-copyright',
        'liw_settings_page',
        plugins_url('/images/poo-solid.svg', __FILE__)
    );

    // 创建子菜单
    add_submenu_page(
            'liw-copyright',
        'add poo title',
        'add poo',
        'manage_options',
        'liw_copyright_add_poo',
        'liw_create_submenu_add_poo_func'
    );
}
function liw_settings_page() { ?> <h2>liw poo home menu</h2> <?php }
function liw_create_submenu_add_poo_func() { ?> <h2>submenu add poo</h2> <?php }
```

## widgets 小工具创建和使用

在外观里面有widgets，可以排列组合成为自己想要的。
