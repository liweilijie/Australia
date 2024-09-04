# Australia
my dream about Australia.

哈哈哈，精心备战了2个多月我的PTE终于考过了，刚刚好雅思6分首考过了，太开心了，如果再给我一个月七炸应该没问题的，不过不重要了，我要求没有那么高。
接下来全身心投入到java的世界里面啦。

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

## 待学习

- [springboot-guide](https://github.com/CodingDocs/springboot-guide)
- [一套全面又有实际意义的axios封装+api管理方案](https://github.com/slevin57/Blog/issues/11)
