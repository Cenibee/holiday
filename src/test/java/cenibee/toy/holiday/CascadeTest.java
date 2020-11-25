package cenibee.toy.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cenibee.toy.holiday.cascade.Person;
import cenibee.toy.holiday.cascade.PersonRepository;
import cenibee.toy.holiday.cascade.Phone;
import cenibee.toy.holiday.cascade.PhoneRepository;

@DataJpaTest
class CascadeTest {

	@Autowired private TestEntityManager entityManager;
	@Autowired private PersonRepository personRepository;
	@Autowired private PhoneRepository phoneRepository;

	@Test
	void persist() throws Exception {
		Person person = new Person();
		person.setId( 1L );
		person.setName( "John Doe" );

		Phone phone = new Phone();
		phone.setId( 1L );
		phone.setNumber( "123-456-7890" );

		person.addPhone( phone );

		personRepository.save(person);
		
		assertEquals(phone, phoneRepository.findById(phone.getId()).get());
	}
}
