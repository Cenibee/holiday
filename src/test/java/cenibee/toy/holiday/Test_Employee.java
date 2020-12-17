package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.common.NotEnoughHolidayException;
import cenibee.toy.holiday.employ.Employee;
import cenibee.toy.holiday.history.HolidayConsumedHistory;
import cenibee.toy.holiday.history.HolidayHistory;
import cenibee.toy.holiday.history.HolidayProducedHistory;

@ExtendWith(MockitoExtension.class)
public class Test_Employee {
	@Test
	void addHistoryWithProduced() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayProducedHistory.class);

		// when:
		emp.updateHoliday(history);

		// then:
		verify(empHoliday, only()).increase(historyHoliday);
		verify(empHoliday, never()).decrease(historyHoliday);
	}

	@Test
	void addHistoryWithComsumed() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayConsumedHistory.class);

		// when:
		emp.updateHoliday(history);

		// then:
		verify(empHoliday, only()).decrease(historyHoliday);
		verify(empHoliday, never()).increase(historyHoliday);
	}

	@Test
	void addHistoryWithComsumedOnException() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayConsumedHistory.class);

		doThrow(new NotEnoughHolidayException("test exception")).when(empHoliday).decrease(any());

		// expect:
		assertThrows(NotEnoughHolidayException.class, () -> emp.updateHoliday(history));
		verify(empHoliday, only()).decrease(historyHoliday);
		verify(empHoliday, never()).increase(historyHoliday);
	}

	@Test
	void addHistoryWithIlligal() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayHistory.class);

		// expect:
		assertThrows(IllegalArgumentException.class, () -> emp.updateHoliday(history));
	}
	
	Employee mockEmployeeWithHoliday(Holiday holiday) {
		Employee emp = new Employee();
		emp.setHoliday(holiday);
		return spy(emp);
	}

	<T extends HolidayHistory> T mockHistoryWithHolidayAndList(Holiday holiday, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		T history = clazz.newInstance();
		if(holiday != null)
			history.setHoliday(holiday);
		return spy(history);
	}
}
