package iuh.fit.se.models;

import java.util.List;

public class UserSession {
    private String username;
    private List<String> authorities;

    public UserSession(String username, List<String> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public boolean hasAuthority(String auth) {
        return authorities.contains(auth);
    }

    public boolean hasRole(String role) {
        return hasAuthority("ROLE_" + role);
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
