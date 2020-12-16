package cenibee.toy.holiday.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Spec_Holiday {
	@Test
	void increaseTest() {
		Holiday holiday = new Holiday(0, false);

		holiday.increase(new Holiday(5, false));
		assertEquals(5, holiday.getHolidayCount(), "휴일: 0.0 + 5.0 = 5.0개");
		assertEquals(false, holiday.getRestHalfOff(), "휴일: 0.0 + 5.0 = 5.0개");

		holiday.increase(new Holiday(3, true));
		assertEquals(8, holiday.getHolidayCount(), "휴일: 5.0개 + 3.5개 = 8.5개");
		assertEquals(true, holiday.getRestHalfOff(), "휴일: 5.0개 + 3.5개 = 8.5개");

		holiday.increase(new Holiday(2, false));
		assertEquals(10, holiday.getHolidayCount(), "휴일: 8.5개 + 2개 = 10.5개");
		assertEquals(true, holiday.getRestHalfOff(), "휴일: 8.5개 + 2개 = 10.5개");

		holiday.increase(new Holiday(1, true));
		assertEquals(12, holiday.getHolidayCount(), "휴일: 10.5개 + 1.5개 = 12.0개");
		assertEquals(false, holiday.getRestHalfOff(), "휴일: 10.5개 + 1.5개 = 12.0개");
	}

	@Test
	void decreaseTest() throws Exception {
		Holiday holiday = new Holiday(13, false);

		holiday.decrease(new Holiday(5, false));
		assertEquals(8, holiday.getHolidayCount(), "휴일: 12.0 - 5.0 = 7.0개");
		assertEquals(false, holiday.getRestHalfOff(), "휴일: 12.0 - 5.0 = 7.0개");

		holiday.decrease(new Holiday(3, true));
		assertEquals(4, holiday.getHolidayCount(), "휴일: 7.0개 - 3.5개 = 3.5개");
		assertEquals(true, holiday.getRestHalfOff(), "휴일: 7.0개 - 3.5개 = 3.5개");

		holiday.decrease(new Holiday(2, false));
		assertEquals(2, holiday.getHolidayCount(), "휴일: 3.5개 - 2개 = 1.5개");
		assertEquals(true, holiday.getRestHalfOff(), "휴일: 3.5개 - 2개 = 1.5개");

		holiday.decrease(new Holiday(1, true));
		assertEquals(1, holiday.getHolidayCount(), "휴일: 1.5개 - 1.5개 = 0.0개");
		assertEquals(false, holiday.getRestHalfOff(), "휴일: 1.5개 - 1.5개 = 0.0개");

		assertThrows(NotEnoughHolidayException.class,
				() -> holiday.decrease(new Holiday(1, true)),
				"휴일이 부족하면 NotEnoughHolidayException 발생");
	}

	@Test
	void toStringTest() throws Exception {
		Holiday holiday1 = new Holiday(133, true);
		Holiday holiday2 = new Holiday(0, false);
		Holiday holiday3 = new Holiday(14, false);

		assertEquals("휴가: 133.5", holiday1.toString());
		assertEquals("휴가: 0.0", holiday2.toString());
		assertEquals("휴가: 14.0", holiday3.toString());
	}
}
