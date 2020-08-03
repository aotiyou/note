https://juejin.im/post/5ab7bd11f265da23906bfbc5

#### JDBC相关概念

一种典型的桥接模式。

>  桥接模式是一种结构型设计模式，它的主要特点是把抽象与行为实现分离开来，分别定义接口，可以保持各部分的独立性以及应对他们的功能扩展。

##### JDBC规范

用Connection代表和数据库的连接，用Statement执行SQL，用ResultSet表示SQL返回的结果，提供了对数据的便利。从Connection可以创建Statement，Statement执行查询得到ResultSet。

##### 数据库驱动

抽象了Driver驱动的概念

``` 
Connection con=MySqlConnectionImpl("127.0.0.1",3306,"mi_user",userName,pwd);
```

##### 数据源

DriverManager是JDBC1提供的，DataSource是JDBC2新增的功能，提供了更好的连接数据源的方法。

#### 对比Hibernate和MyBatis

Hibernate和Mybatis都是ORM模型，Hibernate提供的是一种全表映射的模型，对JDBC的封装程度比较高。但Hibernate也有不少缺点，列举如下：

- 全表映射带来的不便，比如更新时需要发送所有的字段；
- 无法根据不同的条件组装不同的SQL；
- 对多表关联和复杂SQL查询支持较差，需要自己写SQL，返回后，需要自己将数据组装为POJO；
- 不能有效支持存储过程；
- 虽然有HQL，但性能较差，大型互联网系统往往需要优化SQL，而Hibernate做不到。

#### 核心组件

核心组件主要包括以下几个：

- SqlSessionFactoryBuilder：会根据配置信息或代码来生成SqlSessionFactory；
- SqlSessionFactory：依靠工厂来生成SqlSession；
- SqlSession：是一个既可以发送SQL去执行并返回结果，也可以获取Mapper的接口；
- SQL Mapper：是MyBatis新设计的组件，由一个Java接口和XML文件构成，需要给出对应的SQL和映射规则。它负责发送SQL去执行，并返回结果。

##### 构建SqlSessionFactory

每个MyBatis应用都是以SqlSessionFactory的实例为中心的，它的任务是创建SqlSession。SqlSesion类似于一个JDBC的Connection对象。

提供了2种方式创建SqlSessionFactory：一种是XML配置的方式，一种是代码的方式，推荐使用XML配置的方式。

##### 创建SqlSession

SqlSession是一个接口类，扮演者门面的作用，真正干活的是Executor接口。需要保证每次用完正常关闭它。

##### 映射器

映射器是由Java接口和XML文件（或注解）共同组成的，作用如下：

- 定义参数类型
- 描述缓存
- 描述SQL语句
- 定义查询结果和POJO的映射关系

##### 组件生命周期

SqlSessionFactory在MyBatis应用的整个生命周期中，每个数据库只对应一个SqlSessionFactory，可以实现一个工具类，以单例模式获取该对象。

SqlSession的生命周期在请求数据库处理事务的过程中，它是一个线程不安全的对象，在涉及多线程的时候要特别当心。它存活于一个应用的请求和操作，可以执行多条SQL，保证事务的一致性。

Mapper的作用是发送SQL，然后返回需要的结果，或者执行SQL修改数据库的数据，所以它应该在一个SqlSession事务方法之内，如同JDBC中一条SQL语句的执行，它最大的范围和SqlSession是相同的。

