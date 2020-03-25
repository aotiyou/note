source:https://www.cnblogs.com/lanxuezaipiao/p/3635556.html

JNI（Java Native Interface）它允许Java代码和其他语言（尤其C/C++）写的代码进行交互，只要遵守调用约定即可。

JNI调用C/C++的过程（写程序时自下而上，调用时自上而下）：

<img src="C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200312181235021.png" alt="image-20200312181235021" style="zoom: 80%;" />

JNA(Java Native Access)是一个开源的Java框架，是Sun公司推出的一种调用本地方法的技术，是建立在经典的JNI基础上的一个框架。

JNA抵用C/C++过程：

<img src="C:\Users\zxy\AppData\Roaming\Typora\typora-user-images\image-20200312184011568.png" alt="image-20200312184011568" style="zoom:80%;" />

步骤减少了很多吗，最重要的是我们不需要重写我们的动态链接库文件，而是有直接调用的API，大大简化了我们的工作量。

JNA只需要我们写Java代码而不用写JNI或本地代码。

搜索动态连：接库路劲的顺序是：先从当前类的当前文件夹找，如果没有找到，再在工程当前文件夹下面罩win32/win64文件夹，找到后搜索对应的dll文件，如果找不到再到WINDOWS下面去搜索，找不到就抛异常了。

类型映射（Type Mappings），JNA官方给出的默认类型映射表如下：

| Native type | Size                | java type  | Common windows types |
| :---------- | ------------------- | ---------- | -------------------- |
| char        | 8-bit integer       | byte       | BYTE,TCHAR           |
| short       | 16-bit integer      | short      | WORD                 |
| wchar_t     | 16/32-bit character | chat       | TCHAR                |
| int         | 32-bit integer      | int        | DWORD                |
| int         | boolean value       | boolean    | BOOL                 |
| long        | 32/64-bit integer   | Nativelong | LONG                 |
| long long   | 64-bit integer      | long       | _int64               |
| float       | 32-bit FP           | float      |                      |
| double      | 64-bit FP           | double     |                      |
| char*       | C string            | String     | LPTCSTR              |
| void*       | pointer             | Pointer    | LPVOID,HANDLE,LPXXX  |

