package cenibee.toy.holiday.accessingdatarest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// CHECK1 - 이거는 런타임에 구현이 자동생성 되는데, 이에 대한 자세한 구현은 @RepositoryRestMvcConfiguration 에서 확인할 수 있다.
// ?-/persons로 자동생성 되는걸 people로만 바꿨다는 의미인가???
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	List<Person> findByLastName(@Param("name") String name);
}
