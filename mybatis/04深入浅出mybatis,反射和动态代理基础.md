https://juejin.im/post/5abcbd946fb9a028d1412efc

#### 目录

* 构建SqlSessionFactory过程
* 映射器的动态代理
* SqlSession的4大对象
* sql执行的过程

#### 构建SqlSessionFactory过程

构建主要分为2步：

* 通过XMLConfigBuilder解析配置的XML文件，读出配置参数，包括基础配置XML文件和映射器XML文件；

* 使用Configuration对象创建SqlSessionFactory，SqlSessionFactory是一个接口，提供了一个默认的实现类DefaultSqlSessionFactory。

说白了，就是将我们的所有配置解析为Configuration对象，在整个生命周期内，可以通过该对象获取需要的配置。

##### MappedStatement

它保存映射器的一个节点（select|insert|delete|update），包括配置的SQL，SQL的id、缓存信息、resultMap、parameterType、resultType等重要配置内容。

它涉及的对象比较多，一般不去修改它。

##### SqlSource

它是MappedStatement的一个属性，主要作用是根据参数和其他规则组装SQL，也是很复杂的，一般也不用修改它。

##### BoundSql

对于参数和SQL，主要反映在BoundSql类对象上，在插件中，通过它获取到当前运行的SQL和参数以及参数规则，作出适当的修改，满足特殊的要求。

BoundSql提供3个主要的属性：parameterObject、parameterMappings和sql，下面分别来介绍。

#### SqlSession运行过程

##### 映射器的动态代理

Mapper映射是通过动态代理来实现的，使用JDK动态代理返回一个代理对象，供调用者访问。

##### SqlSession下的四大对象

Mapper执行的过程是通过Executor、StatementHandler、ParameterHandler和ResultHandler来完成数据库操作和结果返回的，理解他们是编写插件的关键：

- Executor：执行器，由它统一调度其他三个对象来执行对应的SQL；
- StatementHandler：使用数据库的Statement执行操作；
- ParameterHandler：用于SQL对参数的处理；
- ResultHandler：进行最后数据集的封装返回处理；

在MyBatis中存在三种执行器：

- SIMPLE：简易执行器，默认的执行器；
- REUSE：执行重用预处理语句；
- BATCH:执行重用语句和批量更新，针对批量专用的执行器；

