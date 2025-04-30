package tobyspring.helloboot.containerless;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

//@MySpringBootApplication
@SpringBootApplication
public class ContainerlessBootApplication {
    private final JdbcTemplate jdbcTemplate;

    public ContainerlessBootApplication(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    ApplicationRunner run(ConditionEvaluationReport report){
        return arg -> {
            report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch())
                    .filter(co -> !co.getKey().contains("Jmx"))
                    .forEach(co->{
                        System.out.println(co.getKey());
                        co.getValue().forEach(c->{
                            System.out.println(c.getOutcome());
                            //어떤 조건을 통과했는지도 볼 수 있음.
                        });
                        System.out.println();
                    });
        };
        //매칭된 것만 봄
    }

    @PostConstruct
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(ContainerlessBootApplication.class, args);
    }
}