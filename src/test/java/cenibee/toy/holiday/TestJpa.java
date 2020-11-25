package cenibee.toy.holiday;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = "spring.profiles.active=test")
public class TestJpa {
    
    // @Autowired
    // private TestEntityManager entityManager;

    // @Autowired
    // private EmployeeRepository repository;

    @Test
    void testExample() throws Exception {
    }
}
