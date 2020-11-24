package cenibee.toy.holiday.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// <Entity, Id Type>

public interface UserRepository extends CrudRepository<User, Integer> {
	
}