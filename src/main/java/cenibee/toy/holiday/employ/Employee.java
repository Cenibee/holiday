package cenibee.toy.holiday.employ;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cenibee.toy.holiday.common.Holiday;
import cenibee.toy.holiday.common.NotEnoughHolidayException;
import cenibee.toy.holiday.department.Department;
import cenibee.toy.holiday.history.HolidayConsumedHistory;
import cenibee.toy.holiday.history.HolidayHistory;
import cenibee.toy.holiday.history.HolidayProducedHistory;
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

    // TODO 서비스 추상화 레벨에서 다루어주어야 한다.
    @NonNull
    private Holiday holiday;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "EMPLOYEE_BELONG_TO_DEPARTMENT"))
    private Department department;

    /**
     * Employee 의 연차 개수를 업데이트합니다.<br/><br/>
     * 연차 개수가 부족한 경우 업데이트 되지 않고 {@link NotEnoughHolidayException} 예외가 발생합니다.<br/><br/>
     * history 가 올바른 타입이 아닌 경우 {@link IllegalArgumentException} 예외가 발생합니다.
     * 
     * @param history - 추가할 연차 이력 ({@link HolidayConsumedHistory} 혹은 {@link HolidayProducedHistory} 타입)
     */
    public void updateHoliday(HolidayHistory history) {
        if(history instanceof HolidayProducedHistory)
            this.holiday.increase(history.getHoliday());
        else if(history instanceof HolidayConsumedHistory)
            this.holiday.decrease(history.getHoliday());
        else
            throw new IllegalArgumentException("유효하지 않은 HolidayHistory 타입: HolidayConsumedHistory 혹은 HolidayProducedHistory 여야 합니다.");
    }
}
