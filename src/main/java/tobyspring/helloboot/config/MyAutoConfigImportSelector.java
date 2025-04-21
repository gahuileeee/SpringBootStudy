package tobyspring.helloboot.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector ( ClassLoader classLoader){
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //자동 구성에 사용할 config 목록을 가져오는 메서드
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        //이 메서드가 읽어오는 경로는 아래와 같다.
        //resources/META-INF/spring/파라미터로 넘겨준 클래스의 풀 경로.imports
        return StreamSupport.stream(candidates.spliterator() , false).toArray(String[]::new);
    }
}
