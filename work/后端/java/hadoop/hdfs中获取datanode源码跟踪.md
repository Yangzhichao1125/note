

# java获取服务端datanode源码过程

## 1、进入open方法

![屏幕快照 2019-05-07 下午2.27.29](img/屏幕快照 2019-05-07 下午2.27.29.png)

![屏幕快照 2019-05-07 下午2.29.33](img/屏幕快照 2019-05-07 下午2.29.33.png)

## 2、在FileSystem中得到抽象方法，open（）具体实现为DistributeFileSystem类中

![屏幕快照 2019-05-07 下午2.30.02](img/屏幕快照 2019-05-07 下午2.30.02.png)

## 3、匿名实现类，执行该方法的doCall方法

![屏幕快照 2019-05-07 下午2.30.25](img/屏幕快照 2019-05-07 下午2.30.25.png)

## 4、进入 new DFSInputStream中读取文件

![屏幕快照 2019-05-07 下午2.30.52](img/屏幕快照 2019-05-07 下午2.30.52.png)

## 5、进入openInfo（）

![屏幕快照 2019-05-07 下午2.31.20](img/屏幕快照 2019-05-07 下午2.31.20.png)

## 6、进入fetchLocateBlocksAndGetLastBlockLength（）方法

![屏幕快照 2019-05-07 下午2.31.32](img/屏幕快照 2019-05-07 下午2.31.32.png)

## 7、getLocatedBlocks（）方法获取服务端datenode数据

![屏幕快照 2019-05-07 下午2.31.59](img/屏幕快照 2019-05-07 下午2.31.59.png)

## 8、具体数据如下： 

```java
LocatedBlocks{
  fileLength=417
  underConstruction=false
  blocks=[LocatedBlock{BP-2009215117-127.0.1.1-1556536617485:blk_1073741828_1004; getBlockSize()=417; corrupt=false; offset=0; locs=[DatanodeInfoWithStorage[10.211.55.5:50010,DS-278107b9-d85e-4d61-8c15-6c176ac55007,DISK]]}]
  lastLocatedBlock=LocatedBlock{BP-2009215117-127.0.1.1-1556536617485:blk_1073741828_1004; getBlockSize()=417; corrupt=false; offset=0; locs=[DatanodeInfoWithStorage[10.211.55.5:50010,DS-278107b9-d85e-4d61-8c15-6c176ac55007,DISK]]}
  isLastBlockComplete=true} 
```

