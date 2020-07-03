1.在tomcat/bin/shutdown.sh文件中增加一个参数
> 在文件最后一行的命令添加一个force
>
> exec "$PRGDIR"/"$EXECUTABLE" stop -force "$@"

![image-20200703104742902](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200703104742902.png)

2.在tomcat/bin/catalina.sh文件中，加入这四行

> if [ -z "$CATALINA_PID" ]; then
>     CATALINA_PID=$PRGDIR/CATALINA_PID
>     cat $CATALINA_PID
> fi

![image-20200703104920230](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200703104920230.png)

3.在tomcat/bin/setenv.sh文件中，加入这行：

> CATALINA_PID=$CATALINA_HOME/bin/CATALINA_PID

![image-20200703105026242](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200703105026242.png)

4.结果

![image-20200703105131426](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200703105131426.png)