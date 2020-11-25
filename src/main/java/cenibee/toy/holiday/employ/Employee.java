package cenibee.toy.holiday.employ;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Employee {
    // TODO 실제 사원 넘버가 존재하면 AUTO -> IDENTITY로 변경
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull private String name;
    @NonNull private String department;
    @NonNull private Date entranceDate;
    
    @NonNull private Float holidayCount;
    @NonNull private Float extraHolidayCount;

    private Date resetDate;
}
