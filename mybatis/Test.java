package cn.com.infosec.ra.jt.core;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {

    @org.junit.Test
    public void test(){
        Main main = new Main();
        main.test();
    }

}

class Main implements TestInterface{

    @Override
    public void test() {
        System.out.println("Main执行");
    }
}
