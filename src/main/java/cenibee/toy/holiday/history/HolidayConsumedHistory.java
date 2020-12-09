package cenibee.toy.holiday.history;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO 발생/소모 정책 결정
 * https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance
 */
@Getter
@Setter
public class HolidayConsumedHistory extends HolidayHistory {
	private Boolean isCanceled;
}
