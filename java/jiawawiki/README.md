# Wiki system.

## log

make `logback-spring.xml` into `src/main/resources` directory，the Springboot will auto find it.

由于很多线程可能同时在运行，这样打印出来 的日志并没有和业务线程区分开来，导致看起来特别混乱的。所以我们在日志里面加上一个业务线程的惟一号比较易于查看分析日志。

主要步骤如下：
- 在日志打印的时候加上日志流水号LOG_ID, 在日志文件logback-spring.xml里面增加`%green(%-18X{LOG_ID})`
- 在AOP的入口使用LOG这个自带的增加变量的方法增加一个惟一的ID号给LOG_ID:
   ```java
   public void doBefore(JoinPoint joinPoint) throws Throwable {
       // 增加日志流水号
       MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
   }
   ```
 - 定时器不会被**AOP**拦截，所以单独在定时器里面加一下。 
   ```java
   @Scheduled(cron = "5/30 * * * * ?")
   public void cron() {
       // 增加日志流水号
       MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
   }
   ```

## mybatis

```xml
<!-- 集成mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.3</version>
</dependency>
        <!-- 集成mysql连接 -->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>8.0.22</version>
</dependency>
```

in the `application.properties`to setting **mysql** information：

```properties
# &allowMultiQueries=true 为了在一个sql里面执行多条语句
spring.datasource.url=jdbc:mysql://192.168.50.240:3306/auwiki?characterEncoding=UTF8&autoReconnect=true&serverTimezone=Asia/Shanghai&allowMultiQueries=true
spring.datasource.username=auwiki
spring.datasource.password=dev123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

workflow:
1. prepare the sql for create `test` table in mysql database.
2. new package domain, and to create java class: `domain/Test.class`
    ```java
    public class Test{}
    ```
3. new package mapper, and to create interface: `mappper/TestMapper.java`.
    ```java
    public interface TestMapper {
        public List<Test> list();
    }
    ```
4. new directory `resources/mapper`, and to create xml: `resources/mapper/TestMapper.xml`, to write sql to get the data.
5. add the `@MapperScan("com.jiawa.wiki.mapper")` in WikiApplication for scan the `mapper/*.java`.
6. add the xml config in `application.properties`: `mybatis.mapper-locations=classpath:/mapper/**/*.xml`, classpath=resources path.
7. all of the above, setting all mybatis content. and so we will create business flow. we create `service/TestService.java`.
    ```java
    public class TestService {
        public List<Test> list() {
            return testMapper.list();
        }
    }
    ```
8. and to controller call this service.

and now we will use the mybatis generator to auto generate code:
1. include the mybatis generators plugins in pom.xml.
    ```xml
    <!-- mybatis generator 自动生成代码插件 -->
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.4.0</version>
        <configuration>
            <configurationFile>src/main/resources/generator/generator-config.xml</configurationFile>
            <overwrite>true</overwrite>
            <verbose>true</verbose>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.22</version>
            </dependency>
        </dependencies>
    ```
2. add the generator config in `resources/generator/generator-config.xml`, and to editor this file.
3. add the cmd: Run/Debug Configurations -> new Maven -> Command line: `mybatis-generator:generate -e` and name: `mybatis-generator`.

## Cors

## Pagehelper 分页

settings:
```properties
# 打印mapper 里面Sql语句的日志
logging.level.com.jiawa.wiki.mapper=trace
```

在pom.xml之中增加依赖：
```xml
<!-- pagehelper 插件-->
<dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper-spring-boot-starter</artifactId>
   <version>1.2.13</version>
</dependency>
```

```java
public List<EbookResp> list(EbookReq req) {
    // 在查询语句前加一句分页的设置，PageHelper只对它最近的一条select有效果。
   PageHelper.startPage(pageNum, pageSize);
   List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
    // 获取总数据和当前多少页
   PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
   LOG.info("总行数: {}", pageInfo.getTotal());
   LOG.info("总页数: {}", pageInfo.getPages());
}
```

# Validation

- 集成spring-boot-starter-validation 依赖:
- 对保存接口和查询接口增加参数校验功能
- 校验不通过时，前端弹出错误提示

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

增加校验规则:
```java
public class PageReq {
    @NotNull(message = "【页码】不能为空")
    private int page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 1000, message = "【每页条数】不能超过1000")
    private int size;
}
```

开启校验规则`@Valid`
```java
@PostMapping("/save")
public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
   CommonResp resp = new CommonResp<>();
   ebookService.save(req);
   return resp;
}
```

出错了之后要会利用统一异常处理返回给前端，不然前端会不知道怎么处理。会被统一异常处理拦截到`BindException`从而进行处理。

## 统一异常处理

增加一个类用来做统一异常处理：`controller/ControllerExceptionHandler`


## AOP

Filter > Interceptor filter因为它是在tomcat容器里面。所以最先执行的,范围比较大。

Filter: 过滤器是一个servlet, servlet就是一个请求。 doFilter()一个方法, 自己写后面的业务方法。
Interceptor: 拦截器， Spring框架特有的，常用于登录校验，权限校验，请求日志打印, preHandle(), postHandle() 前后分开的，所以不需要去写调用后面的业务方法, return false后面的业务逻辑就不会再走了。还要增加一个配置文件SpringMvcConfig.java才能使用。
Aspect: AOP

## Redis

配置依赖：
```xml
<!--整合redis-->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

配置redis服务器地址端口密码等信息：
```properties
spring.redis.host=192.168.50.240
spring.redis.port=6379
spring.redis.password=Wiki%1022
```

如何使用: **放入redis里面的内容需要序列化或者是json格式的才可以。**
```java
public void redisDemo() {
   @Resource
   private RedisTemplate redisTemplate;

    // set
   redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

   // delete
   redisTemplate.delete(token);
}
```

## 线程本地变量

只在本线程里面有用，不会影响其他线程，而且在线程的这条生命周期内随时可以获取。

## crontab 定时任务如何执行

不需要引入任何依赖，在`WikiApplication`里面启用定时任务：`@EnableScheduling`
- 所有的定时器都是在一个线程里面的。
- 只有等上一次执行完成，下一次才会在下一个时间点执行，错过就错过

**quartz 才是做真正定时任务的，SpringBoot自带这种都太弱机了。**


## Websocket

websocket使用流程：
- 配置pom.xml
- config/WebSocketConfig.java
- controller类似的流程文件：websocket/WebSocketServer.java 里面写方法
- 比如点赞成功之后向前端推送消息：webSocketServer.sendInfo(); 进行推送消息。

前端：
- footer.vue里面发起websocket连接,接收消息，关闭消息等。


## Springboot 异步化

- `@EnableAsync` 在`Application`里面启用异步化
- `@Async` 在异步化函数上面启动异步化。它会为它生成一个代理类来运行。不要直接在`websocket`内的函数增加，**一定要用另外一个类给他包一层函数来执行。这里就涉及3个类，一个webserver,一个业务类，一个发消息的类为了做代理发消息的类。一定一定要注意。这一点和事务类似的。@Transactional**
   ```java
   @Service
   public class WsService {

      @Resource
      public WebSocketServer webSocketServer;

      @Async
      public void sendInfo(String message, String logId) {
         MDC.put("LOG_ID", logId); // 为了继承LOG_ID流水号，比较好排查是哪个流水线下来的任务。
         webSocketServer.sendInfo(message);
      }
   }
   ```
  
- `@Async`开启线程池，让其不会把资源占完。需要配置一个线程池文件，**TODO:**

## RocketMQ

比较简单, 越复杂会让逻辑越复杂。


## 多线程后台任务如何执行

## web development

Springboot项目发布，多环境配置。运行线上的环境：

- application-prod.properties 配置不一样的比如mysql用户名和密码和表
- Run/Debug Configurations里面以前的SpringBoot/WikiApplication里面增加一个Environment VM options: `-Dspring.profiles.active=prod`, 它会读取prod里面的配置来replace application.properties里面的配置信息。生产环境的时候用的。
- maven install 打包 target/wiki-0.0.1.jar 生成的目标文件。pom.xml里面finalName 最终生成包的名称。
- upload wiki.jar to ECS
- running command: `java -jar -Dspring.profiles.active=prod wiki/wiki.jar` 这样默认是用prod里面的配置来replace application.properties里面的配置信息的。不可以用：`java -jar wiki/wiki.jar` 这样默认是只会读取application.properties里面的配置，而不会读取prod里面的配置的。
- use daemon to protective the java process. recommend `supervisor`.
 
install nginx and setting it.

读取web静态网页的nginx的配置web.conf:

```config
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

反向代理java服务端 server.conf：

```conf
server{
  listen 80;
  # server_name 8.133.184.84;
  server_name wiki-server.courseimooc.com;

  location / {
    proxy_pass http://localhost:8880;

    # 针对websocket，需要增加下面的配置
    proxy_redirect off;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";
    proxy_set_header Host $host:$server_port;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

    # 代理时长设置600秒，默认60秒，websocket超时会自动断开
    proxy_read_timeout 600s;
  }

}
```

Vue项目发布
- build web: `npm build-prod` to dist

域名配置

- 购买域名, 有的域名是不可以备案的，有的可以。
- 国内的ECS一定要备案，国外的或者香港的就可以不用。
- 在阿里云备案，购买一台ECS就可以了。
- 解析A IPV4地址 就可以了。