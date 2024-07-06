# css

做个项目，css 成了最难的了，一直耽搁了很多天没有推进，我也是醉了。

## css 基础知识

**任意一种 class**都有效

```css
.classA, .classB {}

<div class="classA"></>
<div class="classB"></>
```

同时拥有两个 class 才有效, 没有空格：

```css
.classA.classB {
}

<div class="classA classB"></div>
```

classA 元素内部所有声明了 classB 的元素都有效(空格隔开)

```css
.classA .classB {
}

<div class="classA">
	<div class="classB"></div>
	<div>
		<div class="classB"></div>
    </div>
</div>
```

classA 内部第一层声明了 classB 的所有元素有效

```css
.classA > .classB {
}

<div class="classA">
	<div class="classB">有效</div>
	<div>
		<div class="classB">无效</div>
    </div>
    <div class="classB">有效</div>
</div>
```

标签的对应组：

```css
/*所有<div>,<p>都生效*/
div,
p {
}
/*<div>内部的所有<p>都生效*/
div p {
}
/*<div>内部第一层<p>生效*/
div > p {
}
```

class 与标签的混合
基本混合

```css
/*声明了classA的所有<div>*/
div.classA {
}
/*声明了classA的元素，内部的所有<div>有效*/
.classA div {
}
```

逗号、空格、小于号(<), 意义是一样的，无论两边是 class 还是 html 元素

```css
.css1 {
    /* 居中对齐 inline-block 像文字一样 *
    vertical-align: center;
    /* vertical-align: top; */
    /* vertical-align: bottom; */

    /*background-repeat: no-repeat; !* 不平铺 *!*/
    /*background: red url(./test.png);*/
    /*background: red url() no-repeat;*/
    /*background-position: -34px -10px;*/
    /*background-position: center center; !* 横向中间，上下也中间 *!*/
    /*background-position: 20px 30px; !* 横向中间，上下也中间 *!*/
    /*background-size: 100px 50px; !* 背景缩小 *!*/

    margin-bottom: 200px;

    /*border: 1px solid red;*/
    border-bottom: 30px solid red;
    border-right: 30px solid green;
    font-weight: bold;
    font-style: italic;
    text-decoration: none;
    cursor: pointer; /* 手型指针 */

    font-size: 20px !important;
}
```
