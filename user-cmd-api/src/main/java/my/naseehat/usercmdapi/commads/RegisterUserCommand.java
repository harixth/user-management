package my.naseehat.usercmdapi.commads;

import lombok.Builder;
import lombok.Data;
import my.naseehat.usercore.entities.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
