package cstv.Interfaces;

import cstv.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Long> {

    Role findByRole(String role);
}
