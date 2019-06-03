# 安装mongodb



### 1:创建package包

mkdir /usr/local/package

### 2:本级上传tar包到虚拟机的package目录下

scp mongodb-linux-x86_64-rhel62-3.4.3.tgz root@10.211.55.7:/usr/local/package/

//或者从网上下载

wget https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-rhel62-3.4.3.tgz

### 3:解压文件到  /usr/local/mongodb  目录下

cd /usr/local/packages

tar -zxvf mongodb-linux-x86_64-rhel62-3.4.3.tgz -C /usr/local/

cd ..

mv mongodb-linux-x86_64-rhel62-3.4.3/ mongodb

### 4 在安装目录下创建 data 文件夹用于存放数据和日志

cd mongodb/
mkdir data
mkdir data/db
mkdir data/logs

touch data/logs/mongodb.log
touch data/mongodb.conf
vim data/mongodb.conf

```#端口号 port = 27017
#数据目录
dbpath = /usr/local/mongodb/data/db
#日志目录
logpath = /usr/local/mongodb/data/logs/mongodb.log #设置后台运行
fork = true
#日志输出方式
logappend = true
#开启认证
#auth = true
```



### 启动 MongoDB 服务器

/usr/local/mongodb/bin/mongod -config /usr/local/mongodb/data/mongodb.conf

显示以下信息说明启动成功

```
about to fork child process, waiting until server is ready for connections.
forked process: 31768
child process started successfully, parent exiting
```

### 访问 MongoDB 服务器

/usr/local/mongodb/bin/mongo

退出用快捷键 control+C 

### 停止 MongoDB 服务器

/usr/local/mongodb/bin/mongod -shutdown -config /usr/local/mongodb/data/mongodb.conf

注意：停止的时候也要加配置文件路径



