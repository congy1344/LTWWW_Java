package iuh.fit.se.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class AuthResponse {
    private String token;
    private String tokenType;
    private String username;
    private List<String> authorities;

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
