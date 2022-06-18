package my.naseehat.usercmdapi.controllers;

import lombok.extern.slf4j.Slf4j;
import my.naseehat.usercmdapi.commads.RegisterUserCommand;
import my.naseehat.usercore.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
@Slf4j
public class UpdateUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable String id,
                                                   @RequestBody RegisterUserCommand cmd) {
        cmd.setId(id);
        commandGateway.send(cmd);
        try {
            commandGateway.sendAndWait(cmd);
            return ResponseEntity.ok(new BaseResponse("User successfully updated!"));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update user request for id: " + id;
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage));
        }
    }
}
