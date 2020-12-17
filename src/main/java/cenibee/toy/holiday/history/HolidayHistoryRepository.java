package cenibee.toy.holiday.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import cenibee.toy.holiday.employ.Employee;

public interface HolidayHistoryRepository extends CrudRepository<HolidayHistory, Long> {

	Page<HolidayHistory> findByEmployee(Employee employee, Pageable pageable);

}
