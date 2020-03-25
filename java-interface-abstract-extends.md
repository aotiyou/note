source：https://blog.csdn.net/b271737818/article/details/3950245

abstract class 和 interface 是支持抽象类定义的两种机制。

![image-20200325110050106](C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200325110050106.png)

interface 可以继承 interface

abstract 可以实现 interface

acstract 可以继承 object

相同点：

a)两者都是抽象类，都不能实例化

b)interface实现类及abstract的子类都必须要实现已经声明的抽象方法

不同点：

a)interface要用implements，abstract要用extends

b)一个类可以实现多个interface，只能继承一个abstrace

c)interface强调特定功能的实现，而abstract强调所属关系

d)interface，必须全部实现，abstract，可以有选择的实现

这个选择有两点含义：

I)abstract class中并非所有的方法都是抽象的，只有abstract的方法是抽象的，子类必须实现。那些没有abstract的方法，在abstract class中必须定义方法体。

II)abstract class 的子类在继承它时，对非抽象方法既可以直接继承，也可以覆盖；而抽象方法，可以选择实现，也可以通过再次声明其方法为抽象的方式，无需实现，留给其子类来实现，但此类也必须声明为抽象类

e) abstact class是interface与class的中介

interface 是完全抽象的，只能声明方法，而且只能声明public方法，不能声明private及protected方法，不能定义方法体，也不能声明实体变量。

interface

