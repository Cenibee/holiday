package cenibee.toy.holiday.employ;

import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// TODO Spring Security
@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends Repository<Employee, Long>{
	// 사원 삭제
	void delete(Employee entity);
	
	// 추후 join 하게 될 경우 외래키로부터 참조를 위해
	Optional<Employee> findById(Long id);

	// TODO save는 지양하는게 좋을것 같은데 자주 업데이트 하는 넘은 객체 분리도 생각해보자 업데이트 간소화가 있나?
	// 사원 추가
	// 연차 업데이트
	// reset date 업데이트
	// 부서 업데이트
	<S extends Employee> Iterable<S> saveAll(Iterable<S> entities);
	<S extends Employee> S save(S entity);
}
