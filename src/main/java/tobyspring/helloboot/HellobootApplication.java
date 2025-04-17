package tobyspring.helloboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 간편하게 TomcatServletWebServer 을 만들 수 있게 도와주는 클래스
        // SpringBoot 의 ServletWebServerFactory 로 받을 수가 있다.
        // tomcat 뿐만 아니라 다른 WebServer 도 동일한 방식으로 동작시킬 수 있도록 SpringBoot 가 추상화 시켰다.


        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet("hello", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                String name = req.getParameter("name");

                resp.setStatus(HttpStatus.OK.value());
                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                resp.getWriter().println("Hello " + name);
            }
        }).addMapping("/hello"));

        // ServletContextInitializer 을 파라미터로 넘김
        // 이 인터페이스는 onStartup 메소드를 구현해서 서블릿, 필터, 리스너 등을 직접 등록할 수 있게 도와줌 (web.xml 설정을 대체함 )

        //이 인터페이스는 onStartup 이 메소드만이 있고, 파라미터로 ServletContext 가 있음. 따라서 람다식으로 표현함.

        // servlet 을 하나 등록해 보겠음. addServlet 으로 등록하고, 첫 번째 파라미터에는 servlet name 을 넘겨줌
        // 두 번째는 Servlet 인터페이스를 구현한 객체를 넣어줌.
        // HttpServlet 중 service 메소드만 오버라이딩 했음. 응답을 생성해줌 (상태 , 해더, 바디)
        // 이건 /hello 라는 url 에 매핑되어 이곳으로 요청이 들어오면 이 서블릿이 실행될 것임

        webServer.start(); //Tomcat Servlet Container 가 동작하게 됨.
    }
}
