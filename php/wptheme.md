# wordpress 主题

WordPress主题中只绝对需要两个文件：

index.php – 主模板文件
style.css – 主样式文件

区块主题中的主题支持
在区块主题中，下列主题支持被自动启用。

add_theme_support( 'post-thumbnails' );
add_theme_support( 'responsive-embeds' );
add_theme_support( 'editor-styles' );
add_theme_support( 'html5', array('style','script', ) );
add_theme_support( 'automatic-feed-links' );


你必须添加必要的add_action()语句以确保myfirsttheme_setup函数被加载。


在经典主题中，模板标签是内置的WordPress函数，你可以在模板文件中使用，以检索和显示数据，如the_title()和the_content()。

在区块主题中，区块被用来代替模板标签。

在区块主题中，模板片段必须放置在名为parts的文件夹中。

在区块主题中，使用区块而不是模板标签。区块标记是WordPress用于显示区块的HTML代码。模板片段是区块，可以使用与添加区块相同的方式将其添加到模板文件中。

要包含一个页眉或页脚模板片段，请为模板片段添加区块标记。slug是该片段的名称。如果你想包含的文件叫header.html，那么slug就是 "header"：

<!-- wp:template-part {"slug":"header"} /-->
(your page content)
<!-- wp:template-part {"slug":"footer"} /-->
要包含搜索表单，请使用搜索块的块标记：

<!-- wp:search {"label":"Search","buttonText":"Search"} /-->


有几种默认的文章类型可供用户使用，也供WordPress安装内部使用，最常见的有：

文章 (Post类型: ‘post’)
页面 (Post类型: ‘page’)
附件 (Post类型: ‘attachment’)
修订版 (Post类型: ‘revision’)
导航菜单 (Post类型: ‘nav_menu_item’)
区块模板 (Post类型: ‘wp_template’)
模板片段 (Post类型: ‘wp_template_part’)
上面的文章类型可以通过插件或主题进行修改和删除，但不建议您删除广泛分布的主题或插件的内置功能。

