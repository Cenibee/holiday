package cenibee.toy.holiday.cascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
@EqualsAndHashCode(exclude = "phones")
@Entity
public class Person {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setOwner(this);
    }
}
