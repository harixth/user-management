package my.naseehat.userqueryapi.handlers;

import my.naseehat.usercore.events.UserRegisteredEvent;
import my.naseehat.usercore.events.UserRemovedEvent;
import my.naseehat.usercore.events.UserUpdatedEvent;

public interface IUserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);

}
