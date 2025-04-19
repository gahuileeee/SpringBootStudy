package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.helloboot.containerless.HelloController;
import tobyspring.helloboot.containerless.SimpleHelloService;

public class ContainerlessBootApplication {

    public static void main(String[] args) {
        //Spring Container 를 만들것임
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class); //인터페이스가 아닌 정확한 클래스를 빈으로 등록해야 함.

        //스프링 컨테이너가 알아서 빈들을 자기 순서대로 만들어줌
        //컨트롤러를 만들어야 하는데 파라미터로 헬로 서비스가 필요한 경우 -> 자기의 빈들 다 탐색해서 파라미터로 넘겨주어 빈으로 만듦.
        //여러개를 빈으로 등록하는경우? -> @Primary 를 통해 기본 빈을 지정하거나 @Qualifier 로 명시적으로 주입할 수도 있음.
        // 이것을 해주지 않는다면 NoUniqueBeanDefinitionException 에러가 나게됨.

        //기존에는 xml 으로 빈을 등록해주고 생성자에다 어떤 빈을 주입할 지도 기술을 했었음

        applicationContext.refresh(); //bean object 를 만들어줌 (모든 초기화가 일어남)

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        WebServer webServer = serverFactory.getWebServer(servletContext ->
                servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext) {
                }).addMapping("/*"));

        webServer.start(); //Tomcat Servlet Container 가 동작하게 됨.
    }
}
