package springsecurity.dao.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springsecurity.dao.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
