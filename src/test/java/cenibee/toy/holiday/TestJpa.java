package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.department.Department;
import cenibee.toy.holiday.department.DepartmentRepository;
import cenibee.toy.holiday.employ.Employee;
import cenibee.toy.holiday.employ.EmployeeRepository;

@DataJpaTest(properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestJpa {
    @Autowired
    EmployeeRepository repository;

    @Autowired
    DepartmentRepository deptRepository;

    @Test
    void testExample() throws Exception {
        Department department = deptRepository.findById(1L).get();

        Employee emp = new Employee();
        emp.setName("user");
        emp.setEntranceDate(new Date());
        emp.setMailAddress("user@domain.com");
        emp.setPhNumber("000-0000-0000");
        emp.setDepartment(department);

        Holiday holiday = new Holiday(15, false);

        emp.setHoliday(holiday);
        repository.save(emp);

        Employee foundEmp = repository.findById(emp.getId()).get();

        assertEquals(emp.getName(), foundEmp.getName());
        assertEquals(department.getName(), foundEmp.getDepartment().getName());
        assertEquals(holiday.getHolidayCount(), foundEmp.getHoliday().getHolidayCount());
    }
}
