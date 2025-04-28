package tobyspring.helloboot.standaloneServlet;

public class HelloController {
    public HelloController(SimpleHelloService simpleHelloService) {
    }

    public String hello(String name) {
        return "hello " + name;
    }
}
