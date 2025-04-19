package tobyspring.helloboot.containerless;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
//@Component 를 인식해서 빈으로 등록함 (메타 어노테이션도 포함)
public class ContainerlessBootApplication {

    public static void main(String[] args) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                //onRefresh 는 ApplicationContext 가 초기화 되는 과정에 호출되는 후처리 훅 메서드.
                super.onRefresh(); // 생략하면 안 됨. 생략하게 되면 기본적인 Bean 초기화 로직이 날아가게 됨.

                // onRefresh 시점 (컨테이너 초기화가 끝난 시점) 에 dispatcherServlet 초기화
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this) {
                        }).addMapping("/*"));
                webServer.start();
            }
        };
        applicationContext.register(ContainerlessBootApplication.class); //파라미터의 클래스에서 @Bean 메소드들을 읽고 빈 정의로 등록하라

        applicationContext.refresh();
    }
}
