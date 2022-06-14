package my.naseehat.usercmdapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl implements IPasswordEncoder {
    @Override
    public String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        var hashedPassword = encoder.encode(password);
        return hashedPassword;
    }
}
