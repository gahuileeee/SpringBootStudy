package tobyspring.helloboot.standaloneServlet;

public class HelloController {
    public String hello(String name) {
        return "hello " + name;
    }
}
