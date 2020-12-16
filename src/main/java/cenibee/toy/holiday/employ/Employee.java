package cenibee.toy.holiday.employ;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.department.Department;
import cenibee.toy.holiday.history.HolidayHistory;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Date entranceDate;

    @NonNull
    private String mailAddress;

    @NonNull
    private String phNumber;

    @NonNull
    private Holiday holiday;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "EMPLOYEE_BELONG_TO_DEPARTMENT"))
    private Department department;

    // TODO Paginate
    @NonNull
    // TODO cascade
    @OneToMany
    private List<HolidayHistory> holidayHistory;
}
