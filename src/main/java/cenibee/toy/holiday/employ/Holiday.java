package cenibee.toy.holiday.employ;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Holiday {
    @NonNull
    private Integer holidayCount;

    @NonNull
    private Boolean restHalfOff;

    private Integer restTimeOff;
}
