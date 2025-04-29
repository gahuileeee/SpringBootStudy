package tobyspring.helloboot.containerless;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

//@HelloBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;

    @BeforeEach
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed(){
        Assertions.assertThat(helloRepository.findHello("Lemon")).isNull();
    }

    @Test
    void increaseCount(){
        Assertions.assertThat(helloRepository.countOf("Lemon")).isEqualTo(0);

        helloRepository.increaseCount("Lemon");
        Assertions.assertThat(helloRepository.countOf("Lemon")).isEqualTo(1);

        helloRepository.increaseCount("Lemon");
        Assertions.assertThat(helloRepository.countOf("Lemon")).isEqualTo(2);
    }
}
