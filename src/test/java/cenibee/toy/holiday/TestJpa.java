package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cenibee.toy.holiday.employ.Employee;
import cenibee.toy.holiday.employ.EmployeeRepository;

@DataJpaTest(properties = "spring.profiles.active=test")
public class TestJpa {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Value("${my.test}")
    private String test;

    @Test
    void testExample() throws Exception {
        Employee record = new Employee();
        record.setName("name");
        record.setHolidayCount(7);
        this.entityManager.persist(record);
        Employee finded = this.repository.findById(record.getId()).get();
        assertEquals(record.getName(), finded.getName());
        assertEquals(record.getHolidayCount(), finded.getHolidayCount());
    }
}
