package iuh.fit.se.controllers;

import iuh.fit.se.models.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        UserSession user = (UserSession) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        return "home";
    }
}
