package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cenibee.toy.holiday.department.Department;
import cenibee.toy.holiday.employ.Employee;
import cenibee.toy.holiday.employ.EmployeeRepository;
import cenibee.toy.holiday.employ.Holiday;

@DataJpaTest(properties = "spring.profiles.active=test")
public class TestJpa {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Test
    void testExample() throws Exception {
        Department department = new Department();
        department.setName("test team");
        entityManager.persist(department);

        Employee emp = new Employee();
        emp.setName("user");
        emp.setEntranceDate(new Date());
        emp.setMailAddress("user@domain.com");
        emp.setPhNumber("000-0000-0000");
        emp.setDepartment(department);

        Holiday holiday = new Holiday();
        holiday.setHolidayCount(15);
        holiday.setRestHalfOff(false);

        emp.setHoliday(holiday);
        System.out.println("testtest: " + emp.getId());
        entityManager.persist(emp);

        Employee foundEmp = repository.findById(emp.getId()).get();

        assertEquals(emp.getName(), foundEmp.getName());
        assertEquals(department.getName(), foundEmp.getDepartment().getName());
        assertEquals(holiday.getHolidayCount(), foundEmp.getHoliday().getHolidayCount());
    }
}
