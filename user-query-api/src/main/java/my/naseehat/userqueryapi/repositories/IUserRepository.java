package my.naseehat.userqueryapi.repositories;

import my.naseehat.usercore.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
