# Springboot 搭建小总结



1、idea自带脚手架选择对应jar包版本（官网也可快捷获取）

2、dao层Interface上需要加注解@Mapper @Repository

3、不需要mapper.xml 文件，使用

@Insert("INSERT INTO student (NAME) VALUES (#{name})")

代替

4、application.xml 如下

```java
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/demo
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123456

mybatis.type-aliases-package=com.yang.springboot.code.been


logging.path=/
logging.level.com.yang.springboot.code.dao=debug
logging.file=stu.log
```



