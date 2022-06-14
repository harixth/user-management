package my.naseehat.userqueryapi.handlers;

import my.naseehat.usercore.events.UserRegisteredEvent;
import my.naseehat.usercore.events.UserRemovedEvent;
import my.naseehat.usercore.events.UserUpdatedEvent;
import my.naseehat.userqueryapi.repositories.IUserRepository;
import org.axonframework.config.ProcessingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user=group")
public class UserEventHandlerImpl implements IUserEventHandler {

    private final IUserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
