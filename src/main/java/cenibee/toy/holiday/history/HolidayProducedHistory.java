package cenibee.toy.holiday.history;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Produced")
public class HolidayProducedHistory extends HolidayHistory {
	private Date expireDate;
	private Boolean isAnnualHoliday;
}