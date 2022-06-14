package my.naseehat.usercore.events;

import lombok.Builder;
import lombok.Data;
import my.naseehat.usercore.entities.User;

@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
