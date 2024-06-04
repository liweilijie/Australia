# vue

## initial environment

```bash
# install node.js use nvm
nvm install --lts
# set node registry
npm get registry
npm config set registry http://registry.npm.taobao.org

# install vue-cli
npm install -g @vue/cli
# create new web project
vue create hello-world
# use these options: Typescript, Router, Vuex, Linter, n class-style, Babel n, history y, error only, Lint on save, In dedicated config files, save template.

# to run web project
npm run serve
```

select `package.json` and right it, select `Show npm scripts`.

debug: if happened any error like this: `vue-cli-service: command not found please reinstall packages`:
```bash
# clean the cache
npm cache clean --force
# reinstall packages
npm install
```

## axios

install:
```bash
npm install axios --save
```

```js
axios.defaults.baseURL = process.env.VUE_APP_SERVER;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    const token = store.state.user.token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    const response = error.response;
    const status = response.status;
    if (status === 401) {
        // 判断状态码是401 跳转到首页或登录页
        console.log("未登录，跳到首页");
        store.commit("setUser", {});
        message.error("未登录或登录超时");
        router.push('/');
    }
    return Promise.reject(error);
});

console.log('环境：', process.env.NODE_ENV);
console.log('服务端：', process.env.VUE_APP_SERVER);

```



## vue data bind

```ts
// 1. ref()
const ebooks = ref();
ebooks.value = data;
// 2. reactive()
const ebooks2 = reactive({books:[]});
ebooks2.books = data;

return {
    ebooks,
    books: toRef(ebooks2, "books")    
}

```

## Validation