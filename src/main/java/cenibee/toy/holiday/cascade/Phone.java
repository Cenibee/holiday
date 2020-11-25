package cenibee.toy.holiday.cascade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(exclude = "owner")
@Entity
public class Phone {
    @Id
    private Long id;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;
}
