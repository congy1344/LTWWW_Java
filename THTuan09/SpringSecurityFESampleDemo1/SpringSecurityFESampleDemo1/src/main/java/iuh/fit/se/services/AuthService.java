package iuh.fit.se.services;


import iuh.fit.se.models.AuthResponse;
import iuh.fit.se.models.UserSession;

public interface AuthService {

    public AuthResponse login(String username, String password);

    public UserSession loadUser(String token);
}
