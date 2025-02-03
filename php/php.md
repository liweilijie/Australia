# php 基础编程知识

**php.ini**设置时区：

`date.timezone = Australia/Melbourne`

## 全局变量

全局变量：在所有函数外部定义的变量，拥有全局作用域。要在一个函数中访问一个全局变量，需要使用 global 关键字。

```php
<?php
$x=5;
$y=10;
 
function myTest()
{
    global $x,$y;
    $y=$x+$y;
}
 
myTest();
echo $y; // 输出 15
?>
```
**PHP** 将所有全局变量存储在一个名为 **$GLOBALS[index]** 的数组中。 index 保存变量的名称。这个数组可以在函数内部访问，也可以直接用来更新全局变量。
```php
<?php
$x=5;
$y=10;
 
function myTest()
{
    $GLOBALS['y']=$GLOBALS['x']+$GLOBALS['y'];
} 
 
myTest();
echo $y;
?>
```

## static作用域

当一个函数完成时，它的所有变量通常都会被删除。然而，有时候您希望某个局部变量不要被删除。
要做到这一点，请在您第一次声明变量时使用 static 关键字：

```php
<?php
function myTest()
{
    static $x=0;
    echo $x;
    $x++;
}
 
myTest();
myTest();
myTest();
?>
# 输出结果： 012
```

## 常量
常量是一个简单值的标识符。该值在脚本中不能改变。常量在整个脚本中都可以使用。

```php
define ( string $name , mixed $value [, bool $case_insensitive = false ] )
```

> - name：必选参数，常量名称，即标志符。
> - value：必选参数，常量的值。
> - case_insensitive ：可选参数，如果设置为 TRUE，该常量则大小写不敏感。默认是大小写敏感的。

```php
<?php
// 区分大小写的常量名
define("GREETING", "欢迎访问 Runoob.com");
echo GREETING;    // 输出 "欢迎访问 Runoob.com"
echo '<br>';
echo greeting;   // 输出 "greeting"
?>
```

## 超级全局变量

- $GLOBALS: $GLOBALS 是一个包含了全部变量的全局组合数组。变量的名字就是数组的键。
- $_SERVER: 是一个包含了诸如**头信息(header)、路径(path)、以及脚本位置(script locations)** 等等信息的数组。这个数组中的项目由 Web 服务器创建。不能保证每个服务器都提供全部项目；服务器可能会忽略一些，或者提供一些没有在这里列举出来的项目。
- $_REQUEST: PHP $_REQUEST 用于收集HTML表单提交的数据。
- $_POST 被广泛应用于收集表单数据，在HTML form标签的指定该属性："method="post"。
- $_GET 同样被广泛应用于收集表单数据，在HTML form标签的指定该属性："method="get"。

## 魔术变量

PHP 向它运行的任何脚本提供了大量的预定义常量。

不过很多常量都是由不同的扩展库定义的，只有在加载了这些扩展库时才会出现，或者动态加载后，或者在编译时已经包括进去了。

有八个魔术常量它们的值随着它们在代码中的位置改变而改变。

> - `__LINE__`	文件中的当前行号
> - `__FILE__`	文件的完整路径和文件名。如果用在被包含文件中，则返回被包含的文件名
> - `__DIR__`	文件所在的目录。如果用在被包括文件中，则返回被包括的文件所在的目录
> - `__FUNCTION__`	函数名称
> - `__CLASS__`	类的名称，本常量返回该类被定义时的名字（区分大小写）
> - `__TRAIT__`	Trait 的名字
> - `__METHOD__`	类的方法名，返回该方法被定义时的名字（区分大小写）
> - `__NAMESPACE__`	当前命名空间的名称（区分大小写）。此常量是在编译时定义的

## 字符串变量

运算符 (.) 用于把两个字符串值连接起来。 strlen(), strpos()

## 数组
在 PHP 中，有三种类型的数组：

> - 数值数组 - 带有数字 ID 键的数组
> - 关联数组 - 带有指定的键的数组，每个键关联一个值
> - 多维数组 - 包含一个或多个数组的数组

**数值array**:

```php
$cars=array("Volvo", "BMW", "Toyota");
```

**关联数组**:

```php
$age=array("Peter"=>"35", "Ben"=>"37", "Joe"=>"43");
```
**多维array**:

```php
$cars = array
(
    array("Volvo", 100, 96),
    array("BMW", 60, 59),
    array("Toyota", 110, 100)
);
```

访问多维数组: `$cars["BMW"][0]`