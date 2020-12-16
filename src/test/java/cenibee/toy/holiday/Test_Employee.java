package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
		@SuppressWarnings("unchecked")
		List<HolidayHistory> historyList = mock(List.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday, historyList);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayProducedHistory.class);

		// when:
		emp.addHolidayHistory(history);

		// then:
		verify(empHoliday, only()).increase(historyHoliday);
		verify(empHoliday, never()).decrease(historyHoliday);
		verify(historyList, only()).add(history);
	}

	@Test
	void addHistoryWithComsumed() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		@SuppressWarnings("unchecked")
		List<HolidayHistory> historyList = mock(List.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday, historyList);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayConsumedHistory.class);

		// when:
		emp.addHolidayHistory(history);

		// then:
		verify(empHoliday, only()).decrease(historyHoliday);
		verify(empHoliday, never()).increase(historyHoliday);
		verify(historyList, only()).add(history);
	}

	@Test
	void addHistoryWithComsumedOnException() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		@SuppressWarnings("unchecked")
		List<HolidayHistory> historyList = mock(List.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday, historyList);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayConsumedHistory.class);

		doThrow(new NotEnoughHolidayException("test exception")).when(empHoliday).decrease(any());

		// expect:
		assertThrows(NotEnoughHolidayException.class, () -> emp.addHolidayHistory(history));
		verify(empHoliday, only()).decrease(historyHoliday);
		verify(empHoliday, never()).increase(historyHoliday);
		verify(historyList, never()).add(history);
	}

	@Test
	void addHistoryWithIlligal() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday, null);

		Holiday historyHoliday = mock(Holiday.class);
		HolidayHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayHistory.class);

		// expect:
		assertThrows(IllegalArgumentException.class, () -> emp.addHolidayHistory(history));
	}

	@Test
	void cancelProducedHistory() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		Employee emp = mockEmployeeWithHoliday(empHoliday, null);

		HolidayHistory history = mock(HolidayProducedHistory.class);

		// expect:
		assertThrows(IllegalArgumentException.class, () -> emp.cancelHolydayHistory(history));
	}

	@Test
	void cancelConsumedHistory() throws Exception {
		// given:
		Holiday historyHoliday = mock(Holiday.class);
		HolidayConsumedHistory history = mockHistoryWithHolidayAndList(historyHoliday, HolidayConsumedHistory.class);
		doReturn(1L).when(history).getId();

		Holiday empHoliday = mock(Holiday.class);
		List<HolidayHistory> list = new LinkedList<>();
		list.add(history);
		Employee emp = mockEmployeeWithHoliday(empHoliday, list);

		// when:
		emp.cancelHolydayHistory(history);

		// then:
		assertTrue(history.getIsCanceled());
		verify(empHoliday, only()).increase(historyHoliday);
	}

	@Test
	void cancelUnexists() throws Exception {
		// given:
		Holiday empHoliday = mock(Holiday.class);
		List<HolidayHistory> list = new LinkedList<>();
		Employee emp = mockEmployeeWithHoliday(empHoliday, list);

		// when:
		assertThrows(NoSuchElementException.class, () -> emp.cancelHolydayHistory(new HolidayConsumedHistory()));
		verify(empHoliday, never()).increase(any());
	}

	Employee mockEmployeeWithHoliday(Holiday holiday, List<HolidayHistory> list) {
		Employee emp = new Employee();
		emp.setHoliday(holiday);
		ReflectionTestUtils.setField(emp, "holidayHistory", list);
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
