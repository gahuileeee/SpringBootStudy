package tobyspring.helloboot.containerless;

import java.util.Objects;

//@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

//    @GetMapping("/hello")
    public String hello(String name) {
//        SimpleHelloService helloService = new SimpleHelloService();
        // 컨트롤러의 역할 중 하나 : 요청을 검증하는 것
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
