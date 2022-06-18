package my.naseehat.userqueryapi.handlers;

import my.naseehat.userqueryapi.dto.UserLookupResponse;
import my.naseehat.userqueryapi.queries.FindAllUsersQuery;
import my.naseehat.userqueryapi.queries.FindUserByIdQuery;
import my.naseehat.userqueryapi.queries.SearchUsersQuery;

public interface IUserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
