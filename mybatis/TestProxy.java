package cn.com.infosec.ra.jt.core;

import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理以后，所有证书操作的方法调用时，都会调用这个invoke方法
        // 并不是任何一个方法都需要执行调用代理对象进行执行

        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                throw ExceptionUtil.unwrapThrowable(t);
            }
        }

        System.out.println("调用" + method.getName() + "方法成功");

        return "";
    }
}
