package cenibee.toy.holiday.employ;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String department;
    private Date entranceDate;
    
    private Integer holidayCount;
    private Integer extraHolidayCount;

    private Date resetDate;
}
