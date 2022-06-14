package my.naseehat.usercmdapi.security;

public interface IPasswordEncoder {
    String hashPassword(String password);
}
