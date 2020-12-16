package cenibee.toy.holiday.common;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Embeddable
public class Holiday {
    @NonNull
    private Integer holidayCount;

    @NonNull
    private Boolean restHalfOff;

    /**
     * holiday 의 휴가 개수만큼 휴가를 추가합니다.
     * @param holiday - 증가할 휴가
     */
    public void increase(Holiday holiday) {
        this.holidayCount += holiday.holidayCount;

        if(this.restHalfOff && holiday.restHalfOff)
            this.holidayCount++;

        this.restHalfOff = this.restHalfOff != holiday.restHalfOff;
    }

    /**
     * holiday 의 휴가 개수만큼 휴가를 삭감합니다.<br/>
     * 삭감할 휴가보다 휴가개수가 적은 경우, 휴가 개수는 유지되고, {@link NotEnoughHolidayException} 예외가 발생합니다.
     * @param holiday - 삭감할 휴가
     */
    public void decrease(Holiday holiday) {
        Integer tempCount = this.holidayCount - holiday.holidayCount;

        if(!this.restHalfOff && holiday.restHalfOff)
            tempCount--;
        
        if(tempCount < 0)
            throw new NotEnoughHolidayException("남은 " + this.toString() + ", 사용 실패한 " + holiday.toString());
        
        this.holidayCount = tempCount;
        this.restHalfOff = this.restHalfOff != holiday.restHalfOff;
    }

    @Override
    public String toString() {
        return String.format("휴가: %d.%d", this.holidayCount, this.restHalfOff ? 5 : 0);
    }
}
