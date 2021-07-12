package springsecurity.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springsecurity.dao.entities.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
