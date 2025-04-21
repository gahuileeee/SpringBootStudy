package tobyspring.helloboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "tobyspring.helloboot.config.autoconfig.DispatcherServletConfig",
                "tobyspring.helloboot.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
