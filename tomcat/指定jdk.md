在tomcat/bin/setclasspath.sh加入这四行

> export JAVA_HOME=/opt/tomcat+jdk/jdk1.8.0_181
>  27 export JRE_HOME=${JAVA_HOME}/jre
>  28 export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:${CLASSPATH}
>  29 export PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin:${PATH}

![image-20200724175750643](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200724175750643.png)