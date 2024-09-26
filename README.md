# Australia

my dream about Australia.

哈哈哈，精心备战了 2 个多月我的 PTE 终于考过了，刚刚好雅思 6 分首考过了，太开心了，如果再给我一个月七炸应该没问题的，不过不重要了，我要求没有那么高。
接下来全身心投入到 java 的世界里面啦。

## redis

```bash
redis-cli -h 127.0.0.1 -p 6379 -a 'thisizmy!PASS'
```

## mysql

```bash
mysql -u {username} -p'{password}' \
    -h {remote server ip or name} -P {port} \
    -D {DB name}

mysql -u root -p'root' \
        -h 127.0.0.1 -P 3306 \
        -D local
```

## java

[install](https://docs.datastax.com/en/jdk-install/doc/jdk-install/installOpenJdkDeb.html):

```bash
# install OpenJDK8
sudo apt-get update
sudo apt-get install openjdk-8-jdk
java -version


# show the java multi version and the path.
sudo update-alternatives --config java

# input to ~/.bashrc or /etc/environment to setting JAVA_HOME
# java
export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java"
source ~/.bashrc
```

mac:

```bash
# 查看系统里面java的版本以及路径
/usr/libexec/java_home -V

# 切换到对应的JDK版本，只需要将JAVA_HOME环境变量为JDK安装路径即可，vim ~/.zshrc
export JAVA_HOME=/Users/liwei/Library/Java/JavaVirtualMachines/corretto-1.8.0_412/Contents/Home
```

## 项目发布流程

1. 购买 RDS 或者 ECS 需要有外网地址
2. 安装 mysql, redis
3. 安装 jdk, 配置：java_home, path, classpath
4. SpringBoot 多环境打包
    - 修改 application-prod.properties, 启动项目的时候用-Dspring.profiles.active=prod
    - 打包：`mvn install` 生成 xxx.jar 如果需要修改名字，在 pom.xml 里面的`<build><finalName>${artifactId}</finalName></build>` 修改这个值即可。
    - 上传 xxx.jar 到服务器
    - 启动 java 进程： `java -jar -Dspring.profiles.active=prod xxx.jar`
    - 使用 deploy.sh 进行启动 java
    - 查看日志 tail -100f trace.log
5. vue 前端项目发布：
    1. vue 里面多环境配置，使用.env.prod, .env.dev 里面的内容如下
        ```ini
            NODE_ENV=production
            VUE_APP_SERVER=http://server.emacsvi.com
        ```
    2. package.json 里面的 scripts 脚本写法：
        ```json
            "scripts":{
                "serve-dev": "vue-cli-service serve --mode dev --port 8080",
                "serve-prod": "vue-cli-service serve --mode prod",
                "build-dev": "vue-cli-service build --mode dev",
                "build-prod": "vue-cli-service build --mode prod",
                "lint": "vue-cli-service lint"
            }
        ```
    3. 编译完将 dist 里面的静态网页上传到服务器上面
6. nginx 安装配置
    - sudo apt update; sudo apt install nginx
    - /etc/nginx/nginx.conf 里面 use nginx 需要改成 use root 不然会有权限的问题，不用 nginx 用户启动，而是用 root 用户。
    - curl http://ip 看看是否能访问 nginx 页面
7. 解析域名

启动 java 的 jar 包的脚本

**deploy.sh**:

```bash
#!/bin/bash
echo "publish----------"

process_id=`ps -ef | grep wiki.jar | grep -v grep |awk '{print $2}'`
if [ $process_id ] ; then
sudo kill -9 $process_id
fi

source /etc/profile
nohup java -jar -Dspring.profiles.active=prod ~/wiki/wiki.jar > /dev/null 2>&1 &

echo "end publish"
```

web.

nginx -s reload

**web.conf**:

```nginx
server{
  listen 80;
  # server_name 8.133.184.84;
  server_name wiki.courseimooc.com;

  location / {
    alias /root/web/;
    index index.html;
    try_files $uri $uri/ /index.html;
  }

}
```

## 待学习

-   [springboot-guide](https://github.com/CodingDocs/springboot-guide)
-   [一套全面又有实际意义的 axios 封装+api 管理方案](https://github.com/slevin57/Blog/issues/11)
