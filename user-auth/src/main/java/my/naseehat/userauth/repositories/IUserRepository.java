package my.naseehat.userauth.repositories;

import my.naseehat.usercore.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
    @Query("{'account.username': ?0}")
    Optional<User> findByUsername(String username);
}
