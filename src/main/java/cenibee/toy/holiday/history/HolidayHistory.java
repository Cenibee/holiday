package cenibee.toy.holiday.history;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import cenibee.toy.holiday.employ.Holiday;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * TODO 발생/소모 정책 결정
 * https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance
 */
@Getter
@Setter
public class HolidayHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	private Date createdDate;

	@NonNull
	private Holiday holiday;
}
