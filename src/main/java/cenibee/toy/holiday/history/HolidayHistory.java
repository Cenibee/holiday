package cenibee.toy.holiday.history;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.employ.Employee;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("not null")
public class HolidayHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	private Date createdDate;

	@NonNull
	private Holiday holiday;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "EMPLOYEE_HOLIDAY_HISTORY"))
	private Employee employee;
}
