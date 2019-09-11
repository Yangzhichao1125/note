# Docker 搭建 fastDFS



在CentOS7中安装docker

（fastDFS在Mac 的docker中 网络映射有问题，docker在CentOS6.5中安装有问题。所以悬着CentoOS7）

图形化界面转命令行界面：systemctl set-default multi-user.target

（具体可看该文章：https://www.cnblogs.com/vaelailai/p/8037374.html）

安装docker 可参考菜鸟docker教程



安装fastDFS 镜像 ：

```
docker search fastdfs


docker pull delron/fastdfs

docker images

创建容器
docker run -d --network=host --name tracker -v /var/fdfs/tracker:/var/fdfs delron/fastdfs tracker

docker run -d --network=host --name storage -e TRACKER_SERVER=10.211.55.9:22122 -v /var/fdfs/storage:/var/fdfs -e GROUP_NAME=group1 delron/fastdfs storage


```