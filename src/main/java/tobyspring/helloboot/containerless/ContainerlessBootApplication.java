package tobyspring.helloboot.containerless;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.helloboot.config.MySpringBootApplication;

@MySpringBootApplication
public class ContainerlessBootApplication {
    private final JdbcTemplate jdbcTemplate;

    public ContainerlessBootApplication(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(ContainerlessBootApplication.class, args);
    }
}