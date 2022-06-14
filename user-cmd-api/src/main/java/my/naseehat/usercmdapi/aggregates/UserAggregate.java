package my.naseehat.usercmdapi.aggregates;

import my.naseehat.usercmdapi.commads.RegisterUserCommand;
import my.naseehat.usercmdapi.commads.RemoveUserCommad;
import my.naseehat.usercmdapi.commads.UpdateUserCommand;
import my.naseehat.usercmdapi.security.IPasswordEncoder;
import my.naseehat.usercmdapi.security.PasswordEncoderImpl;
import my.naseehat.usercore.entities.User;
import my.naseehat.usercore.events.UserRegisteredEvent;
import my.naseehat.usercore.events.UserRemovedEvent;
import my.naseehat.usercore.events.UserUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final IPasswordEncoder passwordEncoder;

    public  UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand cmd) {
        var newUser = cmd.getUser();
        newUser.setId(cmd.getId());
        passwordEncoder = new PasswordEncoderImpl();
        var password = newUser.getAccount().getPassword();
        var hashPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashPassword);

        var event = UserRegisteredEvent.builder()
                .id(cmd.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand cmd) {
        var updatedUser = cmd.getUser();
        updatedUser.setId(cmd.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashPassword);

        var event = UserRegisteredEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommad cmd) {
        var event = new UserRemovedEvent();
        event.setId(cmd.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
