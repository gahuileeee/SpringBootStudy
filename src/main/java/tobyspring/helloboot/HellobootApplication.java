package tobyspring.helloboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 간편하게 TomcatServletWebServer 을 만들 수 있게 도와주는 클래스
        // SpringBoot 의 ServletWebServerFactory 로 받을 수가 있다.
        // tomcat 뿐만 아니라 다른 WebServer 도 동일한 방식으로 동작시킬 수 있도록 SpringBoot 가 추상화 시켰다.


        // 이제 ServletContainer 를 띄우고 Servlet 을 하나 등록 해 볼 것임.

        // (1)
        // serverFactory.getWebServer 메소드에 파라미터로 ServletContextInitializer  인터페이스를  구현해서 넘겨주어야 함.
        // 이 인터페이스는 onStartup 메소드만이 있고,  서블릿, 필터, 리스너 등을 직접 등록할 수 있게 도와줌 (web.xml 설정을 대체함 )
        // onStartup 메소드의 파라미터로 ServletContext 가 있음. 아래 코드에서는 인터페이스 구현을 람다식으로 표현함.

        // (2)
        // servlet 을 하나 등록해 보겠음. addServlet 으로 등록하고, 첫 번째 파라미터에는 servlet name 을 넘겨줌
        // 두 번째는 Servlet 인터페이스를 구현한 객체를 넣어줌.
        // HttpServlet 중 service 메소드만 오버라이딩 했음. 응답을 생성해줌 (상태 , 해더, 바디)
        // 이건 /hello 라는 url 에 매핑되어 이곳으로 요청이 들어오면 이 서블릿이 실행될 것임

        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet("hello", new HttpServlet() {
            HelloController helloController = new HelloController();
            //매번 인스턴스를 초기화 할 필요 없으니까 앞단에서 HelloController 를 생성함.

            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                // 인증, 보안, 다국어, 공통 기능

                //아래는 매핑 역할
                if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
                    String name = req.getParameter("name");
                    String ret = helloController.hello(name);

//                    resp.setStatus(HttpStatus.OK.value()); 기본적으로 200번 상태로 셋팅해서 리턴해줌
                    resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println(ret);
                }
                else if (req.getRequestURI().equals("/user")){
                    //
                }else{
                    //아무 것도 매핑되지 못한다면 404 error 를 return
                    resp.setStatus(HttpStatus.NOT_FOUND.value());
                }
            }
        }).addMapping("/hello"));

        webServer.start(); //Tomcat Servlet Container 가 동작하게 됨.
    }
}
