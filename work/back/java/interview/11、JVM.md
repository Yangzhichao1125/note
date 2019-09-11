# JVM

## XX参数

1、布尔型

![img](https://img-blog.csdnimg.cn/20190531083808964.png)

![img](https://img-blog.csdnimg.cn/20190531084632873.png)

2、KV型

![img](https://img-blog.csdnimg.cn/20190531084909900.png)

![img](https://img-blog.csdnimg.cn/20190531084931675.png)

jps -l 查进程号

jinfo -flag PrintGCDetails 12680 查看PrintGCDetails是否开启



## 常用参数：

![img](https://img-blog.csdnimg.cn/20190531144317160.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTkxMDY5NA==,size_16,color_FFFFFF,t_70)

```java
    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms)="+totalMemory+"（字节）"+(totalMemory/(double)1024/1024)+"(MB)");
        System.out.println("MAX_MEMORY(-Xms)="+maxMemory+"（字节）"+(maxMemory/(double)1024/1024)+"(MB)");
    } 
```



-XX:MetaspaceSize=512m

```java
jinfo -flag MetaspaceSize 2993
-XX:MetaspaceSize=536870912
```

1. 典型设置案例
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190531145408255.png)





PrintGCDetails

![img](https://img-blog.csdnimg.cn/20190531150033152.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MTkxMDY5NA==,size_16,color_FFFFFF,t_70)

### SurvivorRatio讲解

1. 概述
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190531151124768.png)

### NewRatio讲解

------


![在这里插入图片描述](https://img-blog.csdnimg.cn/20190531151652653.png)



### 软引用和弱引用的适用场景

假如有一个应用需要读取大量的本地图片：

如果每次读取图片都从硬盘读取则会严重影响性能，
如果一次性全部加载到内存中又可能造成内存溢出。
此时使用软引用可以解决这个问题。

设计思路是：用一个HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，JVM会自动回收这些缓存图片对象所占用的空间，从而有效地避免了00M的问题。

```java
Map<String,SoftReference>imageCache=new HashMap<String,SoftReference>(); 
```



//当key为null时才能被清除

```java
    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();

        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);

    }
```

虚引用用于跟踪GC回收状态

