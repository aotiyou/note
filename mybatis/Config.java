package cn.com.infosec.ra.jt.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化配置
 */
@Configuration
@ComponentScan(value="cn.com.infosec.ra.jt.core")
public class Config {

    /**
     * 自动注入封装好的model对象
     * @param factory
     */
    @Autowired
    public void init(DefaultListableBeanFactory factory){
        TestScanner testScanner = new TestScanner(factory);
        //指定扫描哪个包下的class
        testScanner.doScan("cn.com.infosec.ra.jt.core");
    }

    @Autowired
    public void test(TestInterface testInterface){
        testInterface.test();
    }
}
