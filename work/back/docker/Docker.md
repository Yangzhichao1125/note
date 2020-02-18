# Docker

我觉得我有必要吹一波docker，这玩意儿实在是太牛逼太好用了。

以前我们部署nginx，或者mysql等服务一般是搞个虚拟机，CentOS 或者ubuntu 之类的操作系统去弄这些东西。然后去解压，安装，配置，一大堆事情要做，麻烦的要死。有时还经常因为版本问题或者因为不懂配置，或者操作顺序等等搞得服务起不来，所以还得经常给虚拟机弄个快照，以防灾难性事故。这还不是最重要的，最重要的是虚拟机开销太大，一个操作系统所要花费的cpu，内存，硬盘等等这些东西太占资源。如果开个十几台虚拟机，即使是顶级配置的电脑也会卡的要死。

然后这两天不是开始搞docker了嘛。简直打开了一片新天地。部署个服务就几秒钟就搞定了，完全傻瓜式。配置可以写成一行命令去执行，简直爽到高潮。即使配置错误重新弄一个服务即可，完全不用考虑做错了，要花多少时间去填坑。最最重要的是，docker占的资源很少，少到令人发指，比操作系统少了好几个级别。还有，这东西是开源的。真的是谁不用谁傻逼的一个软件。

怪不得现在大多数公司都要求会使用docker，实在是牛逼。

以下是docker部署mysql的步骤：

```
第一步拉取镜像：docker pull mysql:5.6

第二步创建本地文件：mkdir -p ~/mysql/data ~/mysql/logs ~/mysql/conf

第三部配置密码等东西：docker run -p 3306:3306 --name myMysql -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
```

然后mysql就可以连接啦。几秒钟的事简直无敌



## 解决乱码问题：

docker run --name mymysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci 







## docker基础

前世今生：因为环境配置不一样，开发与运维的相爱相杀

搬家/搬楼

目的：一次封装，到处运行

三要素：仓库，镜像，容器 



docker架构图

cs结构



Exec.  Attach 

镜像：联合文件系统

docker commit

容器数据卷：数据共享和持久化。-v  双向。     ：ro readOnly 只允许主机对容器修改内容

dockerfile

容器内各种共享

entrypoint / cmd