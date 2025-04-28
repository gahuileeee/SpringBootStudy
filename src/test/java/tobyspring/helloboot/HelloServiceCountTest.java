package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tobyspring.helloboot.containerless.HelloRepository;
import tobyspring.helloboot.containerless.HelloService;

@HelloBootTest
public class HelloServiceCountTest {
    @Autowired
    HelloService helloService;
    @Autowired
    HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount(){
        helloService.sayHello("Lemon");
        Assertions.assertThat(helloRepository.countOf("Lemon")).isEqualTo(1);
    }
}
