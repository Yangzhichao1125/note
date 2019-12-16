# Docker 搭建 fastDFS



在CentOS7中安装docker

（fastDFS在Mac 的docker中 网络映射有问题，docker在CentOS6.5中安装有问题。所以选择CentoOS7）

图形化界面转命令行界面：systemctl set-default multi-user.target

（具体可看该文章：https://www.cnblogs.com/vaelailai/p/8037374.html）

安装docker 可参考菜鸟docker教程



安装fastDFS 镜像 ：

https://www.cnblogs.com/yanwanglol/p/9860202.html

```
docker search fastdfs


docker pull delron/fastdfs

docker images

创建容器
docker run -d --network=host --name tracker -v /usr/local/docker/fdfs/tracker:/var/fdfs delron/fastdfs tracker


docker run -d --network=host --name storage -e TRACKER_SERVER=172.18.202.118:22122 -v /usr/local/docker/fdfs/storage:/var/fdfs -e GROUP_NAME=group1 delron/fastdfs storage


```

本地测试：

将一张照片 放置在/usr/local/fdfs/storage目录下，

```
scp /Users/yang/Pictures/down/2019-09-15/1568512396340.jpg root@172.18.202.118:/usr/local/fdfs/storage
```



进入storage容器，进入/var/fdfs目录，运行下面命令：

```
docker exce -it storage /bin/bash
cd /var/fdfs
/usr/bin/fdfs_upload_file /etc/fdfs/client.conf 123.jpg
```





group1/M00/00/00/rBLKdl3vXg2APJpEAAAZoy2J9qE150.jpg