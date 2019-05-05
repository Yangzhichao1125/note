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



## 3、java操作hdfs代码

```java
@Before
public void init() throws IOException, InterruptedException, URISyntaxException{
//     构造一个配置参数封装对象
        Configuration conf = new Configuration();
//		构造一个hdfs的客户端
        fs=FileSystem.get(new URI("hdfs://10.211.55.5:9000"), conf, "root");
    }
```
```java
/*
 * 从本地上传文件到hdfs中
 */
@Test
public void testUpload() throws IllegalArgumentException, IOException{
    fs.copyFromLocalFile(new Path("/Users/yang/java/hadoop/hadoop01/anpai.txt"), new Path("/"));
    fs.close();
}

/*
 * 从hdfs中下载文件到本地
 */
@Test
public void testDownload() throws IllegalArgumentException, IOException{
    fs.copyToLocalFile(false, new Path("/anpai.txt"), new Path("/Users/yang/java/hadoop/hadoop01/课程安排.txt"), true);
    fs.close();
}

/*
 * 文件夹操作
 */
@Test
public void testDir() throws IllegalArgumentException, IOException{
    fs.mkdirs(new Path("/yang"));
    System.out.println("创建了一个文件夹：/yang");

    boolean exists = fs.exists(new Path("/yang"));
    System.out.println("/yang文件夹存在否？"+exists);

    fs.copyFromLocalFile(new Path("/Users/yang/java/hadoop/hadoop01/anpai.txt"), new Path("/yang"));
    System.out.println("成功上传了一个文件到/yang目录下");

    fs.delete(new Path("/yang"), true);
    System.out.println("已经将/yang目录删除");

    boolean exists2 = fs.exists(new Path("/yang"));
    System.out.println("/yang文件夹存在否？"+exists2);
    fs.close();
}

/*
 * 文件信息查看
 */
@Test
public void testFileStatus() throws FileNotFoundException, IllegalArgumentException, IOException{
    //只能列出文件信息
    RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
    while(listFiles.hasNext()){
        LocatedFileStatus fileStatus = listFiles.next();
        System.out.println(fileStatus.getPath().getName());
    }

    System.out.println("-----------------------");
    //能列出文件和文件夹信息
    FileStatus[] listStatus = fs.listStatus(new Path("/"));
    for(FileStatus f:listStatus){
        String type="-";
        if(f.isDirectory()) type="d";
        System.out.println(type+"\t"+f.getPath().getName());
    }
    fs.close();
}

@Test
public void testOthers() throws IllegalArgumentException, IOException {
    //文件偏移量信息
    BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(new Path("/anpai.txt"), 0, 417);
    for (BlockLocation location : fileBlockLocations) {
        System.out.println(location.getOffset());
        System.out.println(location.getNames()[0]);
    }

    //修改文件名
    fs.rename(new Path("/anpai.txt"), new Path("/anpai1.txt"));

    //修改一个文件的副本数量
    fs.setReplication(new Path("/anpai1.txt"), (short) 2);
    fs.close();
}
```


​			

