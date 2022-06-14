package my.naseehat.usercore.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    READ_PRIVILEGE, WRITE_PRIVILEGE;

    @Override
    public java.lang.String getAuthority() {
        return null;
    }
}
