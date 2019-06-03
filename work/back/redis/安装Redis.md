# 安装Redis

// 通过 WGET 下载 REDIS 的源码
 **[bigdata@linux ~]$wget** **http://download.redis.io/releases/redis-4.0.2.tar.gz** 

//或者本地上传

scp redis-4.0.2.tar.gz root@10.211.55.7:/usr/local/packages/

// 将源代码解压到安装目录 

tar -zxvf redis-4.0.2.tar.gz

mv redis-4.0.2 redis

mv redis /usr/local/

// 进入 Redis 源代码目录，编译安装 

cd  /usr/local/redis/

// 安装 GCC 

yum install gcc

// 编译源代码 

make MALLOC=libc

// 编译安装 

make install

// 备份配置文件 

cp redis.conf redis.conf.origin

// 修改配置文件中以下内容
 **[bigdata@linux redis-4.0.2]$ sudo vim /etc/redis.conf** 

**daemonize yes** #37 行 #是否以后台 daemon 方式运行，默认不是后台运行

 **pidfile /var/run/redis/redis.pid** #41 行 #redis 的 PID 文件路径(可选) 

**bind 0.0.0.0** #64 行 #绑定主机 IP，默认值为 127.0.0.1，我们是跨机器运行，所以 需要更改
 **logfile /usr/local/redis/logs/redis.log** #104 行 #定义 log 文件位置，模式 log 信息定向到 stdout，输出到/dev/null(可选)
 **dir /usr/local/redis/data** #188 行 #本地数据库存放路径，默认为./，编译安装 默认存在在/usr/local/bin 下(可选) 



mkdir logs
touch logs/redis.log
mkdir data

在安装完 Redis 之后，启动 Redis 

// 启动 Redis 服务器
 **redis-server redis.conf**  

// 连接 Redis 服务器
 **redis-cli** 

// 停止 Redis 服务器
**redis-cli shutdown** 

