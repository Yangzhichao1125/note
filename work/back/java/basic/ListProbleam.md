# 集合多线程问题

## 1.java.util.ConcurrentModificationException

```java
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
         new Thread(()->{
             list.add(new Date().getTime()+"");
             System.out.println(list);
         },String.valueOf(i)).start();
        }		
```

### 对应的解决方法1:

```java
        List<String> list = new Vector<>();
        for (int i = 0; i < 30; i++) {
         new Thread(()->{
                 list.add(new Date().getTime()+"");
                 System.out.println(list);
         },String.valueOf(i)).start();
        }		
```

### 对应的解决方法2:

Collections是Collection的辅助工具

```java
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
         new Thread(()->{
                 list.add(new Date().getTime()+"");
                 System.out.println(list);
         },String.valueOf(i)).start();
        }
```

### 对应的解决方法3:CopyOnWriteArrayList

```java
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
         new Thread(()->{
                 list.add(new Date().getTime()+"");
                 System.out.println(list);
         },String.valueOf(i)).start();
        }
```

### 小总结

vector与Collections使用synchronize

copyonwritearraylist使用lock与unlock且add一个长度加一

copyonwritearrayset  底层使用copyonwritearraylist

map的并发使用concurrenthashmap

hashmap 与 concurrenthashmap之间的区别

https://www.cnblogs.com/fsychen/p/9361858.html



