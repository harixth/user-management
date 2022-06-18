package my.naseehat.userqueryapi.handlers;

import my.naseehat.usercore.entities.User;
import my.naseehat.userqueryapi.dto.UserLookupResponse;
import my.naseehat.userqueryapi.queries.FindAllUsersQuery;
import my.naseehat.userqueryapi.queries.FindUserByIdQuery;
import my.naseehat.userqueryapi.queries.SearchUsersQuery;
import my.naseehat.userqueryapi.repositories.IUserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryHandlerImpl implements IUserQueryHandler {
    private final IUserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.isPresent() ? new UserLookupResponse(user.get()) : null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        var users = new ArrayList<User>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return new UserLookupResponse(users);
    }
}
