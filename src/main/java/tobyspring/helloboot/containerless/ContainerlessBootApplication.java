package tobyspring.helloboot.containerless;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MySpringBootAnnotation
public class ContainerlessBootApplication {
    public static void main(String[] args) {
        MySpringApplication.run(ContainerlessBootApplication.class, args); //SpringApplication.run 과 유사하게 동작
    }
}