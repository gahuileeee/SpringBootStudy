package tobyspring.helloboot.standaloneServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class StandaloneServletBootApplication {

    public static void main(String[] args) {

         ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 간편하게 TomcatServletWebServer 을 만들 수 있게 도와주는 클래스
        // SpringBoot 의 ServletWebServerFactory 로 받을 수가 있다.
        // tomcat 뿐만 아니라 다른 WebServer 도 동일한 방식으로 동작시킬 수 있도록 SpringBoot 가 추상화 시켰다.

        // 아래는 ServletContainer 에 FrontController 를 등록시키는 코드임.
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet("frontcontroller", new HttpServlet() {
            HelloController helloController = new HelloController(new SimpleHelloService());
            // 1) 서블릿 초기화하는 앞단에서 만듦. 매번 인스턴스를 만들 필요가 없으니까.

            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                //2) 인증, 보안, 다국어, 공통 기능

                //3) 아래는 매핑 역할
                if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
                    String name = req.getParameter("name");

                    String ret = helloController.hello(name);

                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println(ret);
                }
                else if (req.getRequestURI().equals("/user")){
                    //
                }else{
                    //아무 것도 매핑되지 못한다면 404 error 를 return
                    resp.setStatus(HttpStatus.NOT_FOUND.value());
                }
            }
        }).addMapping("/*")); //모든 요청에 대해 실행될 것임.

          webServer.start(); //Tomcat Servlet Container 가 동작하게 됨.
    }
}