# Scala语言快速入门（基本类型）

### 一、Linux和Windows环境安装

这部分跳过，直接使用IDEA进行搭建，和其他编程语言配置差不多

### 二、HelloWorld

- 1.object表示一个伴生对象（相当于一个对象）
- 2.HelloWorld是对象的名字
- 3.def表示声明一个方法
- 4.main表示程序入口
- 5.args:Array[String] 表示形参，和go语言差不多，把参数写前面，类型写在后面
- 6.Unit表示返回值为空（类似Java中的void）

```
object HelloWorld {
 def main(args: Array[String]): Unit = {
    print("Hello World")
  }
}
```

```
科普：反编译的helloworld的执行过程
/**
  * jd-gui
  * 这个工具可用进行反编译成java
  * 我们通过对源文件的反编译，得到一个java的源文件
  * 如果我们使用Java的描述来看,执行流程
  *
  * 1.我们使用object Hello.scala会生成2个.class文件，分别是Hello.class
  * 和Hello$.class
  * 2.调用HelloScala$类的方法 Hello$.MoDULE$.main
  * 3.执行主函数
  */
  
public class HelloWorld {
    public static void main(String[] args) {
        HelloWorld$.MODULE$.main(args);
    }
}
final class HelloWorld${
    public static final HelloWorld$ MODULE$;

    static{
        MODULE$=new HelloWorld$();
    }

    public void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
```

### 三、注释（和Java一样）

```
1.单行注释
//
2.多行注释
/**/
3.文档注释
/**
 *@Param:
 *@Return:
 */
```

### 四、变量

4.1.变量的基本使用

```
object VarDemo {
  def main(args: Array[String]): Unit = {
    var a:Int = 10
    var b:String = "hello"
    var c:Boolean = false
    var d:Double = 10.0
    var f:Float = 10.1f
    println(s"${a}${b}")
  }
}
```

现代编译器，都是动态的，很多编译器都会进行自由化的逃逸分析

**注意事项**：

1.声明变量时，类型可以省略，编译器会自动推导，比如var a:Int = 10可以把:Int简写，这样做，自己本身还是会自动转为强类型，类似

```
println(a.isInstanceOf[Int])
```

2.声明变量我们有2种方式，var或者val，var修饰的变量可以改变，val修饰的变量不可以改变，如果不需要改变，我们推荐使用val，因为线程安全，效率高

```
var a=10
a=""//可以
val b=10
b=""//不可以
```

3.不能不给初始值

```
var a //不可以
```



### 五、程序运算符

1.程序+的使用

```
（1）、左右都是数值，做加法
（2）、左右有一方是字符串，做拼接运算
```



### 六、数据类型

1.在Scala中，数据类型都是对象

2.Scala数据类型分为AnyVal（值类型）和AnyRef（引用类型）

无论值类型，和引用类型，都是对象

```
科普源码：
在scala中，使用Any作为总根
AnyVal是一个分支：Double，Float，Long，Int，Short，Byte，Boolean，Char，StringOps，Unit
AnyRef是一个分支：里面有Scala集合，所有Java类，Null，Nothing和其他Scala类
```

**整型**

1.Scala整型不受OS影响，保证可移植性

2.常量/字面量默认Int型，声明Long型必须加l或者L

3.Scala程序中变量常声明为Int型，除非不足以表示大数，才是用Long

```
记忆：
Byte Short Int Long分别1,2,4,8字节，和Java一样
```

**浮点型**

1.浮点型有固定的范围和长度，不受OS影响

2.默认Double型，声明Float必须加L

3.浮点型可直接表示也可科学计数法

4.通常情况下Double比Float更精确，小数点约后7位

**字符型**

1.Char字符是2个字节

2.允许转义符号，转化为常量

3.可以直接赋值一个整数

4.Char类型可以运算，因为是Unicode编码

**Boolean**

true & false

**Unit & Null & Nothing重点**

  1.我们把Unit进行返回打印，进行测试，打印()

```
  def main(args: Array[String]): Unit = {
    var res=sayHello()
    println(res)
  }
  def sayHello():Unit={
  }
  
  输出：()
```

2.关于Null

```
val dog:Dog=null
val ch:Char=null
```

比较上述两句代码，我们发现，只有Dog不报错，证明，只有AnyRef分支的是可以用null修饰

3.关于Nothing

Nothing是Scala类层级的最低端，是任何其他类型的子类型，当一个函数没有确定返回值，可以返回Nothing



### Question&Answer

1.在Scala REPL（read->evaluteion->print->loop）中，计算3的平方根，然后对该值求平方，求与3相差多少？

**提示：在scala.math中找**
![](https://img2018.cnblogs.com/blog/1449595/201909/1449595-20190925013334697-47199561.png)
2.Scala环境变量配置及其作用
使我们在任何地方能访问到scala

3.Scala程序编写编译，运行步骤是什么？能否一步执行。

编写文件HelloWorld
scalac HelloWorld.scala
scala HelloWorld

4.Scala程序编写的规则。
几乎和Java相同，仅仅后面不加分号

5.Scala语言的SDK是什么？
开发工具包（software development kit）

6.简述，配置环境，编译，运行各个步骤中常见的错误
搭建一下，如果缺少lib包，可以yum upgrade一下，如果还报错，就yum update

7.如何检测一个变量是val还是var？

如果能修改则是val，不能修改是var
8.Scala允许你用数字乘一个字符串，去REPL中试一下“crazy"。如何在scaladoc中找到

![](https://img2018.cnblogs.com/blog/1449595/201909/1449595-20190925013850518-2028703724.png)

9.10 max 2是什么操作？ max定义在什么类中

找到最大值 10 max 2 max 20 ，是返回20

10.用Right计算2的1024次方

**提示：在BigInt找**

![](https://img2018.cnblogs.com/blog/1449595/201909/1449595-20190925014145364-705798485.png)


11.在Scala中如何获取“Hello”的首字符和尾字符？

**提示：在String中找**

![](https://img2018.cnblogs.com/blog/1449595/201909/1449595-20190925014401917-1551193179.png)

# 

（参考视频：av39126512，参考文档：发群里了那个文档）