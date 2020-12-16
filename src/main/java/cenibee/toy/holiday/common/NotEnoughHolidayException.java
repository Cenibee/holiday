package cenibee.toy.holiday.common;

public class NotEnoughHolidayException extends HolidayException{
	private static final long serialVersionUID = 1L;

	public NotEnoughHolidayException(String message) {
		super(message);
	}
}