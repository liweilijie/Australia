# vue

```bash
# setting taobao registry
npm config set registry https://registry.npmmirror.com
```

## 基础知识

computed 和 watch 之间的区别：

1. computed 能完成的功能，watch 都可以完成
2. watch 能完成的功能，computed 不一定能完成，例如： watch 可以进行异步操作，computed 不可以做异步操作维护数据。
3. 两个重要的小原则：
    1. 所有被 vue 管理的函数，最好写成普通函数，这样 this 的指向才是 vm 或者组件实例对象。
    2. 所有不被 vue 所管理的函数（定时器的回调函数，ajax 的回调函数, Promise 的回调函数等），最好写成箭头函数，这样 this 的指向才是 vm 或者组件实例对象，因为它会向上找实例对象。

-   插值语法： 标签体内的值
-   指令语法：标签属性，标签体内部的内容，绑定事件

@click
v-model:

绑定 class 样式, string 写法，适用于：样式的类名不确定，需要动态指定：
:class="mood"

条件渲染：
v-show="false" 只是加了一个 display:none 的样式，结构还是在的
v-if="false" 连结构都不在了
v-if... v-else-if ... v-else-if ... else

template 不破坏结构，且只能与 v-if 使用
<template v-if='n==1'>
</template>
