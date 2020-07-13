package cn.com.infosec.ra.jt.core;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

public class TestScanner extends ClassPathBeanDefinitionScanner {

    public TestScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 1.扫描指定包下class文件
        // 2.注入spring
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitions) {
            GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            // 构造函数的入参 这里的BeanClassName 其实就是接口的className
            genericBeanDefinition.
                    getConstructorArgumentValues().
                    addGenericArgumentValue(genericBeanDefinition.getBeanClassName());
            // 供spring实例化代理对象使用
            genericBeanDefinition.setBeanClass(TestFactoryBean.class);
        }
        return beanDefinitions;
    }

    /**
     * 关键方法：就是因为这个方法的重写，所以TestInterface接口能放到spring中
     * @param beanDefinition
     * @return beanDefinition 是接口 && 独立的类，则返回true
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
