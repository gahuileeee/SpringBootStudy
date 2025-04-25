package tobyspring.helloboot.containerless;

import org.springframework.boot.SpringApplication;
import tobyspring.helloboot.config.MySpringBootApplication;

@MySpringBootApplication
public class ContainerlessBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerlessBootApplication.class, args);
    }
}