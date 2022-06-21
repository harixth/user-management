package my.naseehat.usercmdapi.controllers;

import lombok.extern.slf4j.Slf4j;
import my.naseehat.usercmdapi.commads.RemoveUserCommand;
import my.naseehat.usercore.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/removeUser")
@Slf4j
public class RemoveUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable(value = "id") String id) {
        try {
            commandGateway.send(new RemoveUserCommand(id));

            return ResponseEntity.ok(new BaseResponse("User Successfully deleted"));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing remove user request for id: " + id;
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage));
        }
    }
}
