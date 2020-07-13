https://juejin.im/post/5b00c52ef265da0b95276091

## 事务的特性（ACID）

**1.原子性**：事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用；

**2.一致性**：执行事务前后，数据保持一致；

**3.隔离性**：并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发事务之间数据库是独立的；

**4.持久性**：一个事务被提交之后，它对数据库中数据的改变时持久的，即使数据库发生故障也不应该对其有任何影响。

Spring事务管理接口

* PlatformTransactionManager：（平台）事务管理器
* TransctionDefinition：事务定义信息（事务隔离级别、传播行为、超时、只读、回滚规则）
* TransactionStatus：事务运行状态

**所谓事务管理，起始就是“按照给定的事务规则来执行提交或者回滚操作”**

PlatformTransactionManager接口

Spring并不直接管理事务，而是提供了多种事务管理器。

org.springframework.transaction.PlatformTransactionManager。

PlatformTansactionManager接口定义了三个方法：

```java
public interface PlatformTransactionManager extends TransactionManager {
    // Return a currently active transaction or create a new one, according to the specified propagation behavior（根据指定的传播行为，返回当前活动的事务或创建一个新事务。）
    TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
    // Commit the given transaction, with regard to its status（使用事务目前的状态提交事务）
    void commit(TransactionStatus status) throws TransactionException;
    // Perform a rollback of the given transaction（对执行的事务进行回滚）
    void rollback(TransactionStatus status) throws TransactionException;
}
```

Spring中PlatformTransactionManager根据不同持久层框架所对应的接口实现类

| 事务                                                         | 说明                                          |
| ------------------------------------------------------------ | --------------------------------------------- |
| org.springframework.jdbc.datasource.DataSourceTransactionManager | 使用Spring JDBC或者iBatis进行持久化数据时使用 |
| org.springframework.orm.hibernate3.HibernateTransactionManager | 使用Hibernate3.0版本进行持久化数据时使用      |
| org.springframework.orm.jpa.JpaTransactionManager            | 使用JPA进行数据持久化时使用                   |
|  org.springframework.jta.JtaTransactionManager  |  使用一个JTA实现来管理事务，在一个事务跨越多个资源时使用  |

TransactionDefinition接口

事务管理器接口PlatformTransactionManager通过getTransaction(TransactionDefinition definition)方法来得到一个事务，这个方法里面的参数就是TransactionDefinition类，这个类就定义了一些基本的事务属性。

**那么什么是事务属性呢？**

事务属性可以理解成事务的一些基本配置，描述了事务策略如何应用到方法上。

事务属性包含了5个方面

* 隔离级别
* 传播行为
* 回滚规则
* 是否只读
* 事务超时

TransactionDefinition定义了5个方法以及一些表示事务属性的常量比如隔离级别、传播行为等等常量。

TransactionDefinition接口中的方法：

```java
public interface TransactionDefinition{
    // 返回事务的传播行为
    default int getPropagationBehavior() {return PROPAGATION_REQUIRED;}
    // 返回事务的隔离级别，事务管理器根据它来控制另一个事务可以看到本事务内的哪些数据
    default int getIsolationLevel() {return ISOLATION_DEFAULT;}
    // 返回事务必须在多少秒内完成
    default int getTimeout() {return TIMEOUT_DEFAULT;}
    // 返回是否优化为只读事务
    default boolean isReadOnly() {return false;}
    // 返回事务的名字
    default String getName() {return null;}
}
```

（1）事务隔离级别（定义了一个事务可能受其他并发事务影响的程度）

TransactionDefiniton接口定义了五个表示隔离级别的常量。

**并发事务带来的问题**

* 脏读（Dirty read）：同一条数据，一个事务进行修改，没提交；这时，另一个事务访问这个数据，使用者这个数据。因为这个数据还没提交，那个另一个事务读到的这个数据是“脏数据”，依据“脏数据”所做的操作是可能不正确的。
* 丢失修改（Lost to modify）：同一条数据，一个事务进行修改，没提交；这时，另一个事务也进行修改。这样第一个事务内的修改结果就被丢失，因此称为丢失数据。
* 不可重复读（Unrepeatableread）：同一条数据，一个事务进行两次读取，没提交；这时另一个事务进行修改，这就会发生在一个事务内两次读到的数据是不一样的情况，因此称为不可重复读。

* 幻读（Phantom read）：不可重复读与幻读类似。一个事务读取了几行数据；这时，另一个事务插入一些数据，在随后的查询中，第一个事务就会发现多了一些原本不存在的记录，就好像发生了幻觉一样，因此称为幻读。

不可重复读和幻读区别：

不可重复读的重点是修改，幻读的重点在于新增或者删除。

例1（同样的条件, 你读取过的数据, 再次读取出来发现值不一样了 ）：事务1中的A先生读取自己的工资为     1000的操作还没完成，事务2中的B先生就修改了A的工资为2000，导        致A再读自己的工资时工资变为  2000；这就是不可重复读。

例2（同样的条件, 第1次和第2次读出来的记录数不一样 ）：假某工资单表中工资大于3000的有4人，事务1读取了所有工资大于3000的人，共查到4条记录，这时事务2 又插入了一条工资大于3000的记录，事务1再次读取时查到的记录就变为了5条，这样就导致了幻读。

隔离级别

TransactionDefinition接口中定义了五个表示隔离级别的常量：

* ISOLATION_DEFAULT = -1：使用后端数据库默认的隔离级别。

  * Mysql默认采用REPEATABLE_READ隔离级别
  * Oracle默认采用READ_COMMITTED隔离级别

* ISOLATION_READ_UNCOMMITTED = 1：最低的隔离级别，允许读取尚未提交的数据变更，可能会导致**脏读、幻读、不可重复读**

* ISOLATION_READ_COMMITTED = 2：云度读取并发事务已经提交的数据，可以阻止脏读，可能会导致**幻读、不可重复读**

* ISOLATION_REPEATABLE_READ = 4：对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，可能会导致**幻读**

* ISOLATION_SERIALIZABLE = 8：最高的隔离级别，完全服从CAID的隔离级别。**该级别可以防止脏读、不可重复读以及幻读**。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

事务传播行为（为了解决业务层方法之间互相调用的事务问题）

当事务方法被另一个事务方法调用时，必须指定事务应该如何传播。例如：方法可能继续在现有事务中运行，也可能开启一个新事务，并在自己的事务中运行。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：

**支持当前事务的情况**：

* PROPAGATION_REQUIRED = 0：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
* PROPAGATION_SUPPORTS = 1：如果当前存在事务，则加入该事务，如果当前没有事务，则以非事务的方式继续运行
* PROPAGATION_MANFATORY = 2：如果当前存在事务，则加入该事务，如果当前没有事务，则抛出异常（mandatory：强制性）

**不支持当前事务的情况**：

* PROPAGATION_REQUIRES_NEW = 3：创建一个新的事务，如果当前存在事务，这把当前事务挂起
* PROPAGATION_NOT_SUPPORTED = 4：以非事务方式运行，如果当前存在事务，则把当前事务挂起
* PROPAGATION_NEVER = 5：以非事务方式运行，如果当前存在事务，则抛出异常

**其他情况**：

* PROPAGATION_NESTED = 6：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该值等价于PROPAGATION_REQUIRED

前六中事务传播行为是Spring从EJB中引入的，他们共享相同的概念。而PROPAGATION_NESTED是Spring所特有的。以PROPAGATION_NESTED启动的事务内嵌与外部事务中（如果存在外部事务的话），此时，内嵌事务并不是一个独立的事务，它依赖于外部事务的存在，只有通过外部的事务提交，才能引起内部事务的提交，嵌套的自事务不能单独提交。如果熟悉 JDBC 中的保存点（SavePoint）的概念，那嵌套事务就很容易理解了，其实嵌套的子事务就是保存点的一个应用，一个事务中可以包括多个保存点，每一个嵌套子事务。另外，外部事务的回滚也会导致嵌套子事务的回滚。

事务超时属性（一个事务允许执行的最长时间）

```
int TIMEOUT_DEFAULT = -1;
```

所谓事务超时，就是指一个事务所允许执行的最长时间，如果超过该时间限制但事务还没有完成，则自动回滚事务。在TransactionDefinition中以int的值来表示超时时间，单位是秒

事务只读属性（对事务资源是否执行只读操作）

```
default boolean isReadOnly() {
   return false;
}
```

事务的只读属性是指，对事务性资源进行只读操作或者是读写操作。所谓事务性资源就是指那些被事务管理的资源，比如数据源、JMS资源，以及自定的事务性资源等等。如果确定只对事务性资源惊醒只读操作，那么我们可以量事务标志为只读的，以提高事务处理的性能。在TransactionDefinition中以boolean类型来表示事务是否只读。

回滚规则（定义事务回滚规则）

这些规则定义了哪些异常会导致事务回滚而哪些不会。默认情况下，事务只有遇到运行期异常时才会回滚，而在遇到检查型异常时不会回滚（这一行为与EJB的回滚行为是一致的）。但是你可以声明事务在遇到特定的检查型异常时像遇到运行期异常那样回滚。同样，你还可以声明事务遇到特定的异常不会滚，即使这些异常是运行期异常。

TransactionStatus接口

TransactionStatus接口用来记录事务的状态 该接口定义了一组方法，用来获取或判断事务的相应状态信息

PlatformTransactionManager.gerTransaction(...)方法返回一个TransactionStatus对象。返回的TransactionStatus对象可能代表一个新的或已经存在的事务（如果在当前调用堆栈有一个符合条件的事务）

T