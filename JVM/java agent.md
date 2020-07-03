https://mp.weixin.qq.com/s/7mY_itwcoYjIWHi1oQz3Ow

Java agent提供了一种在加载字节码时，对字节码进行修改的方式。他共有两种方式执行，一种是在main方法执行之前，通过premain来实现，另一种是在程序运行中，通过attach api来实现。

## Instrumenttation

JDK1.5提供的API，用于拦截类加载时间，并对字节码进行修改，主要方法如下:

```java
public interface Instrumenttation {
    // 注册一个转换器，类加载时间会被注册的转换器所拦截
    void addTransformer(ClassFileTransformer transformer, boolean canRetransform);
    // 重新触发类加载
    void retransformClasses(Class<?>... classes) throws UnmodifiableClassException;
    // 直接替换类的定义
    void redefineClasses(ClassDefinition... definitions) throws ClassNotFountException, UnmodifiableClassException;
}
```

### premain

premain是在main方法之前运行的方法，也是最常见的agent方式。运行时需要将agent程序打包成jar包，并在启动时添加命令行来执行，如下文所示：

```
java -javaagent:agent.jar=xunche HelloWorld
```

premain共提供以下两种重载方法，Jvm启动时会先尝试使用第一种方法，若没有会使用第二种方法：

```java
public static void premain(String agentArgs, Instrumentation inst);
public static void premain(String agentArgs);
```







