package cenibee.toy.holiday.common;

public class HolidayException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HolidayException(String message) {
		super(message);
	}

	public HolidayException(Throwable cause) {
		super(cause);
	}

	public HolidayException(String message, Throwable cause) {
		super(message, cause);
	}
}
