package my.naseehat.usercmdapi.commads;

import lombok.Builder;
import lombok.Data;
import my.naseehat.usercore.entities.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
