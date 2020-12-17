package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.department.Department;
import cenibee.toy.holiday.department.DepartmentRepository;
import cenibee.toy.holiday.employ.Employee;
import cenibee.toy.holiday.employ.EmployeeRepository;
import cenibee.toy.holiday.history.HolidayConsumedHistory;
import cenibee.toy.holiday.history.HolidayHistory;
import cenibee.toy.holiday.history.HolidayHistoryRepository;

@DataJpaTest(properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestJpa {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository deptRepository;
    @Autowired
    HolidayHistoryRepository historyRepository;

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
        employeeRepository.save(emp);

        Employee foundEmp = employeeRepository.findById(emp.getId()).get();

        assertEquals(emp.getName(), foundEmp.getName());
        assertEquals(department.getName(), foundEmp.getDepartment().getName());
        assertEquals(holiday.getHolidayCount(), foundEmp.getHoliday().getHolidayCount());
    }

    @Test
    void test_many_to_one() throws Exception {
        Department department = deptRepository.findById(1L).get();

        Employee emp = new Employee();
        emp.setName("user");
        emp.setEntranceDate(new Date());
        emp.setMailAddress("user@domain.com");
        emp.setPhNumber("000-0000-0000");
        emp.setDepartment(department);

        Holiday holiday = new Holiday(15, false);

        emp.setHoliday(holiday);
        employeeRepository.save(emp);
        // entityManager.persist(emp);

        HolidayHistory history1 = new HolidayConsumedHistory();
        history1.setHoliday(new Holiday(3, true));
        history1.setEmployee(emp);
        entityManager.persist(history1);
        HolidayHistory history2 = new HolidayConsumedHistory();
        history2.setHoliday(new Holiday(3, true));
        history2.setEmployee(emp);
        entityManager.persist(history2);
        HolidayHistory history3 = new HolidayConsumedHistory();
        history3.setHoliday(new Holiday(3, true));
        history3.setEmployee(emp);
        entityManager.persist(history3);

        Page<HolidayHistory> result = historyRepository.findByEmployee(emp, PageRequest.of(0, 2));

        assertEquals(0, result.getNumber());
        assertEquals(2, result.getSize());
        assertEquals(3, result.getTotalElements());
        assertEquals(2, result.getTotalPages());

        assertTrue(result.getContent().get(0) == result.getContent().get(1));
    }
}
