package my.naseehat.usercmdapi.commads;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RemoveUserCommad {
    @TargetAggregateIdentifier
    private String id;
}
