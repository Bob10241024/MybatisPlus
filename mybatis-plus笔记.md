# 问题

## 配置文件（application.properties）

![image-20220212120046522](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20220212120046522.png)

```
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#错误配置
#spring.datasource.url=jdbc:mysql://3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
```

## 关于jdbc连接报错，MySQL连接，出现com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure等问题解决方法

### mysql连接的驱动在5.7版本及之前驱动是

```
com.mysql.jdbc.Driver
```

### 在8.0更新之后需要注意,已经换成了以下的需要加cj

```
jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncodeing=UTF-8&amp;useSSL=false&amp;serverTimezone=GMT

```

### 在8.0以上需要注意，有些参数已经被废弃但是必须的参数有以下

```
jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncodeing=UTF-8&amp;useSSL=false&amp;serverTimezone=GMT

```

###  ***!!!连接的符号也从“&”换成”&amp；“注意其他的符号在在xml文件中并不支持，有些参数已经被废弃，以上的都是在MySQL8.0版本出现的问题。***