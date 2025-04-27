package tobyspring.helloboot.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.helloboot.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {
    @Bean
    public ServerProperties serverProperties(Environment env){
        //Binder : application.properties 의 값과 파라미터의 클래스의 필드를 자동 매칭해줌
        return Binder.get(env).bind("", ServerProperties.class).get();
    }
}
