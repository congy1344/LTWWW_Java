package iuh.fit.se.controllers;

import iuh.fit.se.models.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String dashboard(HttpSession session) {
        UserSession user = (UserSession) session.getAttribute("user");

        if (user == null || !user.hasRole("ADMIN")) {
            return "access-denied";
        }

        return "dashboard";
    }
}
