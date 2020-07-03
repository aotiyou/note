https://juejin.im/post/594a24defe88c2006aa01f1c

对象锁

- 对于同一对象

  > synchronized(this|object){}代码块和synchronized修饰非静态方法获取的锁是同一个锁，即该类的对象的对象锁

- 对于不同对象

  > 两个线程访问不同对象的synchronized(this|object){}代码块和synchronized修改非静态方式是异步的，同一个类的不同对象的对象锁互不干扰

类锁

- 对于同一对象

  > synchronized(类.class){}代码块或synchronized修饰静态方法和synchronized(this|object){}代码块和synchronized修饰非静态方法的行为一致

- 对于不同对象

  > 两个线程访问不同对象的synchronized(类.class){}代码块或synchronized修饰静态方法还是同步的，类型synchronized(类.class){}代码块和synchronized修饰静态方法获取的锁是类锁。对于同一个类的不同对象的类锁是同一个。

类中同时有synchronized(类.class){}代码块或synchronized修饰静态方法或synchronized(this|object){}代码块和synchronized修饰非静态方法时会怎样？

> synchronized修饰静态方法和synchronized修饰非静态方法是异步的，对于synchronized(类.class){}代码块和synchronized(this|object){}代码块也是一样的。所以对象锁和类锁是独立的，互不干扰。

1.synchronized关键字不能继承

​	对于父类中的synchronized修饰方法，子类在覆盖方法时，默认情况下不是同步的，必须显示的使用synchronized关键字修饰才行。

2.在定义接口方法时不能使用synchronized关键字

3.构造方法不能使用synchronized关键字，但可以使用synchronized代码块来进行同步。