# **ElasticSearch 安装**



/ 通过 Wget 下载 ElasticSearch 安装包 

**[bigdata@linux ~]$wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearc h-5.6.2.tar.gz** 

修改 Linux 配置参数: 

```
// 修改文件数配置，在文件末尾添加如下配置
[bigdata@linux ~]$sudo vim /etc/security/limits.conf
* soft nofile 65536
* hard nofile 131072
* soft nproc 2048
* hard nproc 4096
// 修改* soft nproc 1024 为 * soft nproc 2048
[bigdata@linux ~]$sudo vim /etc/security/limits.d/90-nproc.conf
* soft nproc 2048 #将该条目修改成2048
// 在文件末尾添加:
[bigdata@linux ~]$sudo vim /etc/sysctl.conf
vm.max_map_count=655360
// 在文件末尾添加:
[bigdata@linux elasticsearch-5.6.2]$ sudo sysctl -p
```



配置 ElasticSearch: 

// 解压 ElasticSearch 到安装目录 

**[bigdata@linux ~]$ tar -xf elasticsearch-5.6.2.tar.gz - C ./cluster/** 

43 

【更多 Java、HTML5、Android、python、大数据 资料，可访问尚硅谷(中国) www.atguigu.com 下载区】 

```
尚硅谷大数据技术之电影推荐系统
```

| // 进入 ElasticSearch 安装目录  **[bigdata@linux cluster]$ cd elasticsearch-5.6.2/** // 创建 ElasticSearch 数据文件夹 data  **[bigdata@linux cluster]$ mkdir elasticsearch-5.6.2/data/** // 创建 ElasticSearch 日志文件夹 logs  **[bigdata@linux cluster]$ mkdir elasticsearch-5.6.2/logs/** // 修改 ElasticSearch 配置文件  								**[bigdata@linux elasticsearch- 5.6.2]$ vim ./config/elasticsearch.yml** |
| ------------------------------------------------------------ |
| **cluster.name: es-cluster** #设置集群的名称 **node.name: es-node** #修改当前节点的名称 |
| **path.data: /home/bigdata/cluster/elasticsearch-5.6.2/data** #修改数 据路径  								**path.logs: /home/bigdata/cluster/elasticsearch-5.6.2/logs** #修改日 志路径  								**bootstrap.memory_lock: false** #设置 ES 节点允许内存交换 **bootstrap.system_call_filter: false** #禁用系统调用过滤器 **network.host: linux** #设置当前主机名称 **discovery.zen.ping.unicast.hosts: ["linux"]** #设置集群的主机列表 |

完成 ElasticSearch 的配置后: 

// 启动 ElasticSearch 服务
 **[bigdata@linux elasticsearch-5.6.2]$ ./bin/elasticsearch -d** // 访问 ElasticSearch 服务
 **[bigdata@linux elasticsearch-5.6.2]$ curl http://linux:9200/** 

**{ "name" : "es-node", "cluster_name" : "es-cluster", "cluster_uuid" : "VUjWSShBS8KM_EPJdIer6g", "version" : {** 

**"number" : "5.6.2",** 

44 

【更多 Java、HTML5、Android、python、大数据 资料，可访问尚硅谷(中国) www.atguigu.com 下载区】 

5.4 **Azkaban**(单节点)环境配置 5.4.1 安装 **Git** 

```
尚硅谷大数据技术之电影推荐系统
```

**"build_hash" : "57e20f3", "build_date" : "2017-09-23T13:16:45.703Z", "build_snapshot" : false, "lucene_version" : "6.6.1"** 

**},** 

**"tagline" : "You Know, for Search" }** 

// 停止 ElasticSearch 服务 **[bigdata@linux ~]$ jps** 

**8514 Elasticsearch 6131 GradleDaemon 8908 Jps** 

**[bigdata@linux ~]$ kill -9 8514** 