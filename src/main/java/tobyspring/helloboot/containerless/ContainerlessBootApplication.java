package tobyspring.helloboot.containerless;

import tobyspring.helloboot.config.MySpringBootApplication;

@MySpringBootApplication
public class ContainerlessBootApplication {
    public static void main(String[] args) {
        MySpringApplication.run(ContainerlessBootApplication.class, args); //SpringApplication.run 과 유사하게 동작
    }
}