## ActiveMQ

### 1、MQ种类

```
kafka
rocketMQ
rabbitMQ
ActiveMQ
```



### 2、MQ的优点

```
解藕
削峰
异步
```



### 3、jms

```
connection
session
destination(que,topic)
JavaEE中的一个规范
包括（中间件，生产者，消费者，消息）
```



### 4、消息

```
消息头（过期时间，id，优先级，是否持久，目的地）
消息属性（识别，去重，重点）
消息体（5种格式text ，map 收发一致）
```



### 5、消息可靠性

```
事务（偏生产者，commit，rollback，消费者没有commit可能重复消费）
签收 （偏消费者，acknowledge）事务大于签收
持久化（默认持久，topic 要先订阅）
多节点集群
```



### 6、broker

```
java自身创建activeMQ实例
```



### 7、nio

```
tcp://0.0.0.0:61616
nio://0.0.0.0:61618
nioauto
amqp,stomp,mqtt
```



### 8、存储和可持久化

```
jdbc
kahaDB（默认）
LEvelDB
AMQ (文件存储，已淘汰)
```



### 9、PERSISTENT

```
队列在msgs表中会去除
topic在ack，msgs表中会记录
```



### 10、Journal

```
快速缓存
批量插入db
```



### 11、高可用

```
zookeeper leveldb 搭建高可用
主从leveldb复制
```



### 12、异步投递

```
慢消费者
没事务持久化
（useAsyncSend）丢失少量数据 客户端
```





Receive 同步阻塞

消息监听listener

