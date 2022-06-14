package my.naseehat.usercore.events;

import lombok.Builder;
import lombok.Data;
import my.naseehat.usercore.entities.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UserUpdatedEvent {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
