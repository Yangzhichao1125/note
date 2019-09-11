# tomcat 下配置apr+native应对高并发



#### 安装依赖：

```sh
yum install apr-devel
yum install openssl-devel
yum install gcc
yum install make
```

#### 下载安装包：

```sh
wget http://apache.fayea.com/apr/apr-1.6.5.tar.gz
wget http://apache.fayea.com/apr/apr-util-1.6.1.tar.gz
```

### 解压软件包

```sh
tar -zxvf apr-1.6.5.tar.gz
tar -zxvf apr-util-1.6.1.tar.gz		
```

#### 开启安装配置：

```sh
cd apr-1.6.5/
./configure --prefix=/usr/local/apr
make &&make install

cd apr-util-1.6.1/

./configure --prefix=/usr/local/apr-util --with-apr=/usr/local/apr
make
--------------------------------------------------------------------
make过程报错：
xml/apr_xml.c:35:19: fatal error: expat.h: No such file or directory
--------------------------------------------------------------------
需要安装：
yum install expat-devel
make install
```

#### 安装解压 native：

```sh
cd /usr/local/tomcat_mzxz_01/bin/
tar -zxvf tomcat-native.tar.gz 
cd tomcat-native-1.2.17-src/native/

./configure --with-apr=/usr/local/apr --with-java-home=/usr/java/jdk1.8.0_151
make
make install
```

#### 设置环境变量：

```sh
vi /etc/profile   

#在文本的最后面添加以下内容 

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/apr/lib   
export LD_RUN_PATH=$LD_RUN_PATH:/usr/local/apr/lib

#使profile生效， 

source /etc/profile
```

#### 修改tomcat

```sh
vim catalina.sh
JAVA_OPTS="$JAVA_OPTS -Djava.library.path=/usr/local/apr/lib"
```

