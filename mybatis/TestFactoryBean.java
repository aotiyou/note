package cn.com.infosec.ra.jt.core;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 这是一个FactoryBean类
 * @param <T>
 */
public class TestFactoryBean<T> implements FactoryBean<T> {

    private Class<T> testClass;

    /**
     *  Spring实例化这个类，就是通过这个构造函数，testClass 就是 TestInterface 接口的class 对象
     *  参数就是TestScanner.doSan()中的.addGenericArgumentValue(xxxx)
     *
     * @param testClass
     */
    public TestFactoryBean(Class<T> testClass) {
        this.testClass = testClass;
    }

    /**
     * 获取TestInterface接口对应的代理类对象，后面依赖TestInterface接口注入的对象就是这个代理对象
     * 这个方法的调用是在AbstractBeanFactory对象里面。具体到方法是getObjectForBeanInstance
     *
     * @return
     * @throws Exception
     */
    @Override
    public T getObject() throws Exception {
        // 生成代理对象的调用链路
        // SqlSession -->Configuration-->MapperRegistry-->MapperProxyFactory->MapperProxy
        return (T) Proxy.newProxyInstance(testClass.getClassLoader(), new Class[]{this.testClass}, new TestProxy());
    }

    @Override
    public Class<?> getObjectType() {
        return this.testClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
