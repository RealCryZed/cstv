package cstv.Interfaces;

import cstv.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("myUserRepository")
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);
}
