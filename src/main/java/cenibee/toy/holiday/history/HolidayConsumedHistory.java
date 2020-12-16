package cenibee.toy.holiday.history;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Consumed")
public class HolidayConsumedHistory extends HolidayHistory {
	private Boolean isCanceled = false;
}
