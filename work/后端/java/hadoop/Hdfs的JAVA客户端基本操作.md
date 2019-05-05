Hdfs的JAVA客户端基本操作



## 1、所需jar包：（版本号根据需求修改）

hadoop-2.4.1\share\hadoop\hdfs\hadoop-hdfs-2.4.1.jar
hadoop-2.4.1\share\hadoop\hdfs\lib\所有jar包

hadoop-2.4.1\share\hadoop\common\hadoop-common-2.4.1.jar
hadoop-2.4.1\share\hadoop\common\lib\所有jar包



## 2、本地写入hdfs

```java
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
//    构造一个配置参数封装对象
        Configuration conf = new Configuration();
//    构造一个hdfs的客户端
        FileSystem fs = FileSystem.get(new URI("hdfs://10.211.55.5:9000"), conf, "root");
//    用hdfs文件系统的客户端对象fs来操作文件，比如上传一个文件
        fs.copyFromLocalFile(new Path("/Users/yang/java/hadoop/hadoop01/anpai.txt"), new Path("/"));
        fs.close();
    }
```

运行报错：

Caused by: java.net.ConnectException: Connection refused

问题原因：

​			ubuntu主机名与本地本地电脑连接host 对应的名字不匹配导致

解决方法：

​			1、获取ubuntu主机名：cat /etc/hostname      #得到主机名为：parallels-vm

​			2、修改本地host：  10.211.55.5 parallels-vm





​			

