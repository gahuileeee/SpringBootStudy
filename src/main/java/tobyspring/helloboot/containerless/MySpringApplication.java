package tobyspring.helloboot.containerless;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass , String... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this); //bean 을 가져왔고 스프링 컨테이너를 전달함
                // -> 이거를 지워도 잘 동작함
                // 스프링 컨테이너가 알아서 필요하겠구나 하고 주입을 해 주었기 때문임.
                // DispatcherServlet 을 보면 ApplicationContextAware 인터페이스가 있음.
                // 이 인터페이스의 구현체는 applicationContext 를 주입받게 됨.

                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this) {
                        }).addMapping("/*"));
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);

        applicationContext.refresh();
    }
}
