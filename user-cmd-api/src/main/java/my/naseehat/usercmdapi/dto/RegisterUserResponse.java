package my.naseehat.usercmdapi.dto;

import my.naseehat.usercore.dto.BaseResponse;

public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
