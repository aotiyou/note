JVM内存包括区域
### Heap（堆区）
>New Generation（新生代）
>> Eden
>> Survivor From
>> Survivor To

>Old Generation（老年代）

### 方法区
>Permanent Generation（持久代）
>Stack（栈）
>Metaspace（元控件）
>Direct ByteBuffer（堆外内存）

### 通过JVM启动参数来配置以上内存空间
Heap内存大小设置
> -Xms512m 设置JVM堆初始内存为512M
> -Xmx1g 设置JVM堆最大可用内存为1g

### New Generation（新生代）内存大小设置
> -Xms256m 设置JVM的新生代内存大小（-Xmn是将NewSize与MaxNewSize设为一致，256m），同下面两个参数：
> > -XX:NewSize=256m
> > -XX:MaxNewSize=256m

### 通过新生代和老年代内存的比值来设置新生代大小
> -XX:NewRatio=3
> 设置新生代（包括Eden和两个Survivor区）与老年代的比值。设置为3，则新生代与老年代所占比值为1:3，新生代占整个堆栈的1/4

### Survivor内存大小设置
> -XX:SurvivorRatio=8
> 设置为8，则两个Survivor区与一个Eden区的比值为2:8，一个Survivor区占整个新生代的1/10

### Eden内存大小设置
> 新生代减去2*Suivivor的内存大小就是Eden的大小

### Old Generation（老年代）的内存大小设置
堆内存减去新生代内存
如上面设置的参数举例如下：
老年代初始内存为：512m-256m=256m
老年代最大内存为：1g-256m=768m

### Stack（栈）内存大小设置
> -Xss1m
> 每个线程都会产生一个栈。在相同物理内存下，减小这个值能生成更多的线程。如果这个值太小会影响方法调用的深度

### Permanent Generation（持久代）内存大小设置
> 方法区内存分配（JDK1.8以前的版本使用，JDK1.8以后就没有持久代了，使用的MetaSpace）
> -XX:PermSize=128m 设置持久代初始内存大小128m
> -XX:MaxPermsize=512m 设置持久代最大内存大小512m

### Direct ByteBuffer（直接内存）内存大小设置
> -XX:MaxDirectMemorySize
> 当Direct ByreBuffer分配的对外内存到达指定大小后，即触发FGC。该值是有上限的，默认是64m，最大为sun.misc.VM.maxDirectMemory()。
> 在程序中可以获得-XX:MaxDirectMemorySize设置的值

### 设置新生代对象进入老年代的年龄
> -XX:MaxTenuringThreshold=15
> 设置垃圾最大年龄。如果设置为0的话，则新生代对象不经过Survivor去，直接进入老年代。
> 对于老年代比较多的应用，可以提高效率。如果将此值设置为一个较大值，则新生代对象会在Survivor区惊醒多次复制，这样可以增加对象在新生代的存货时间，增加在新生代即被回收的概论
> 最大值为15，因为对象头中，用了4位进行存储垃圾年龄【1111(二进制)=15(十进制)】

### 不常用的参数





