linux tar 解压文件时指定文件名

```
[root@netcert jtb]# mkdir ./apache-tomcat-7.0.100_facade_hsm && tar -xzvf apache-tomcat-7.0.100.tar.gz -C./apache-tomcat-7.0.100_facade_hsm --strip-components 1
```

linux 通过进程名查看其占用端口

```  
[root@netcert apache-tomcat-7.0.100_facade_hsm]# ps -ef | grep tomcat
[root@netcert apache-tomcat-7.0.100_facade_hsm]# netstat -nap | grep 4976
[root@netcert apache-tomcat-7.0.100_facade_hsm]# netstat -nap | grep 7100
```

<img src="C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200312154713185.png"  />

![image-20200312154905545](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200312154905545.png)

![image-20200312154914926](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200312154914926.png)