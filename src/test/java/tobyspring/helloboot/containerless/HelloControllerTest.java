package tobyspring.helloboot.containerless;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController(){
        HelloController helloController = new HelloController(name ->name);

        String ret = helloController.hello("Test");
        //spring container 가 없으니 어떻게 집어 넣을 수 있을까? 테스트 스텁을 넣음

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController(){
        HelloController helloController = new HelloController(name ->name);

        Assertions.assertThatThrownBy(()->{
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(()->{
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
