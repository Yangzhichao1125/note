# Docker 运行mysql



以下是docker部署mysql的步骤：

```
第一步拉取镜像：docker pull mysql:5.6

第二步创建本地文件：mkdir -p /usr/local/docker/mysql/data /usr/local/docker/mysql/logs /usr/local/docker/mysql/conf

第三部配置密码等东西：docker run -p 3306:3306 --name myMysql -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
```

解决乱码

```
docker run --name mymysql -p 3306:3306 -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=187600 -d mysql:5.6 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci 
```





