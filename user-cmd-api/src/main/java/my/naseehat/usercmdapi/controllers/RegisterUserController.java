package my.naseehat.usercmdapi.controllers;

import lombok.extern.slf4j.Slf4j;
import my.naseehat.usercmdapi.commads.RegisterUserCommand;
import my.naseehat.usercmdapi.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
@Slf4j
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand cmd) {
        cmd.setId(UUID.randomUUID().toString());
        try {
            commandGateway.sendAndWait(cmd);
            return ResponseEntity.ok(new RegisterUserResponse(cmd.getId(), "User Successfully created"));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id: " + cmd.getId();
            log.error(safeErrorMessage,e);
            return ResponseEntity.internalServerError().body(new RegisterUserResponse(cmd.getId(),
                    safeErrorMessage));
        }
    }
}
