# JAVA一键开启多个服务



在微服务开发的过程中，要启动服务要在idea里面一个一个启动，这个过程重复而又繁琐，那能就想个方法把这个过程简化一下吧。

java的本质是什么？其实java的开发就是一直拼接命令，把命令拼接好，程序也就可以运行了。在idea中我们运行代码是这样：

![yang1](/Users/yang/myNote/git-note/note/work/back/java/images/yang1.png)



复制控制台的第一行数据到终端中运行

![yang2](/Users/yang/myNote/git-note/note/work/back/java/images/yang2.png)



可以看到运行结果与idea中的结果一致。这也就是说，java就是一堆命令的拼接。

![yang3](/Users/yang/myNote/git-note/note/work/back/java/images/yang3.png)

![yang4](/Users/yang/myNote/git-note/note/work/back/java/images/yang4.png)了解到这个特点之后我们就可以用这个方法去一键启动多个服务了。

我们要做的就是

1:将命令复制出来，保存在sh文件中

![yang5](/Users/yang/myNote/git-note/note/work/back/java/images/yang5.png)然后再用sh脚本去跑这些sh文件即可

![yang6](/Users/yang/myNote/git-note/note/work/back/java/images/yang6.png)在这里我是在每个服务都新开一个terminal窗口，然后运行sh文件

(注意：open -a iTerm.app  是mac中新开终端的命令，且若若没有安装iTerm的同学可以使用原生终端，命令为

```sh
open -a Terminal.app xxx.sh
```



执行命令：

```sh
sh base.sh
```

后如图所示：



![yang7](/Users/yang/myNote/git-note/note/work/back/java/images/yang7.png)