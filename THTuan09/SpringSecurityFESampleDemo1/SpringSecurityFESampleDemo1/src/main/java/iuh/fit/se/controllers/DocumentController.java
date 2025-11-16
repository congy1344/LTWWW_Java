package iuh.fit.se.controllers;

import iuh.fit.se.models.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final RestClient restClient;

    public DocumentController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("")
    public String getDocuments(HttpSession session, Model model) {

        String token = (String) session.getAttribute("token");
        UserSession user = (UserSession) session.getAttribute("user");

        if (token == null) return "redirect:/login";

        if (user == null || !user.hasAuthority("DOCUMENT:READ")) {
            return "access-denied";
        }

        try {
            String result = restClient.get()
                    .uri("http://localhost:8081/api/documents")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(String.class);

            model.addAttribute("data", result);
        } catch (Exception ex) {
            model.addAttribute("data", "Lỗi truy cập API: " + ex.getMessage());
        }

        return "document";
    }

    @GetMapping("/create")
    public String createDocument(HttpSession session) {
        UserSession user = (UserSession) session.getAttribute("user");

        if (user == null || !user.hasAuthority("DOCUMENT:WRITE")) {
            return "access-denied";
        }

        return "create-document";
    }

    @PostMapping("/create")
    public String createDocumentSubmit(HttpSession session, Model model) {

        String token = (String) session.getAttribute("token");

        UserSession user = (UserSession) session.getAttribute("user");

        if (user == null || !user.hasAuthority("DOCUMENT:WRITE")) {
            return "access-denied";
        }

        try {
            String result = restClient.post()
                    .uri("http://localhost:8081/api/documents/create")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(String.class);

            model.addAttribute("data", result);

        } catch (Exception ex) {
            model.addAttribute("data", "Lỗi tạo tài liệu: " + ex.getMessage());
        }

        return "create-document";
    }
}
