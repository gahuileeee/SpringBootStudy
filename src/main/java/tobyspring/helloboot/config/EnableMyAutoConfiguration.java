package tobyspring.helloboot.config;

import org.springframework.context.annotation.Import;
import tobyspring.helloboot.config.autoconfig.DispatcherServletConfig;
import tobyspring.helloboot.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {
}
