package cenibee.toy.holiday.employ;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    @NonNull
    private Holiday holiday;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "EMPLOYEE_BELONG_TO_DEPARTMENT"))
    private Department department;

    // TODO Paginate
    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<HolidayHistory> holidayHistory;

    /**
     * Employee 의 연차 개수를 업데이트하고, 연차 이력에 history를 추가합니다.<br/><br/>
     * 연차 개수가 부족한 경우 업데이트와 이력 추가가 되지 않고 {@link NotEnoughHolidayException} 예외가 발생합니다.<br/><br/>
     * 올바른 타입이 아닌 경우 {@link IllegalArgumentException} 예외가 발생합니다.
     * 
     * @param history - 추가할 연차 이력 ({@link HolidayConsumedHistory} 혹은 {@link HolidayProducedHistory} 타입)
     */
    public void addHolidayHistory(HolidayHistory history) {
        if(history instanceof HolidayProducedHistory)
            this.holiday.increase(history.getHoliday());
        else if(history instanceof HolidayConsumedHistory)
            this.holiday.decrease(history.getHoliday());
        else
            throw new IllegalArgumentException("유효하지 않은 HolidayHistory 타입: HolidayConsumedHistory 혹은 HolidayProducedHistory 여야 합니다.");

        this.holidayHistory.add(history);
    }

    /**
     * 소모된 연차 이력을 취소하고, 삭감된 연차를 되돌립니다.<br/><br/>
     * 소모된 연차 이력이 아닌 경우 {@link IllegalArgumentException} 예외가 발생합니다.<br/><br/>
     * history와 동일한 ID를 가지는 이력을 못찾는 경우 {@link NoSuchElementException} 예외가 발생합니다.
     * @param history - 취소할 연차 이력
     */
    public void cancelHolydayHistory(HolidayHistory history) {
        // FUTURE 연차 발생도 취소될 사유가 생길 수 있으니 HolidayHistory로 일단 구현
        if(!(history instanceof HolidayConsumedHistory))
            throw new IllegalArgumentException("유효하지 않은 HolidayHistory 타입: HolidayConsumedHistory 여야 합니다.");

        for(HolidayHistory iteredHistory: this.holidayHistory)
        {
            if((iteredHistory instanceof HolidayConsumedHistory) && history.getId() == iteredHistory.getId())
            {
                ((HolidayConsumedHistory)iteredHistory).setIsCanceled(true);
                this.holiday.increase(iteredHistory.getHoliday());
                return;
            }
        }

        throw new NoSuchElementException("ID 가 " + history.getId() + " 인 연차 이력을 찾을 수 없습니다.");
    }
}
