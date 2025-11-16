package iuh.fit.se.services.impl;


import iuh.fit.se.models.AuthResponse;
import iuh.fit.se.models.UserSession;
import iuh.fit.se.services.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private final RestClient restClient;

    public AuthServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    public AuthResponse login(String username, String password) {
        return restClient.post()
                .uri("http://localhost:8081/api/auth/login")
                .body(Map.of("username", username, "password", password))
                .retrieve()
                .body(AuthResponse.class);
    }

    public UserSession loadUser(String token) {
        var response = restClient.get()
                .uri("http://localhost:8081/api/auth/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(Map.class);

        List<Map<String, String>> rawAuthorities = (List<Map<String, String>>) response.get("authorities");

        List<String> authoritiesList = rawAuthorities.stream()
                .map(authMap -> authMap.get("authority")) // Extract the string value from the "authority" key
                .collect(java.util.stream.Collectors.toList());

        return new UserSession((String) response.get("username"), authoritiesList);
    }
}
