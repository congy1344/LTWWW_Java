package fit.se.thlab4.servlet;

import fit.se.thlab4.dao.UserDAO;
import fit.se.thlab4.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/user-registration")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserRegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/user-registration.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get form parameters
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String reenterEmail = request.getParameter("reenterEmail");
            String password = request.getParameter("password");
            String birthdayStr = request.getParameter("birthday");
            String gender = request.getParameter("gender");

            // Basic validation
            StringBuilder errorMessages = new StringBuilder();
            if (firstName == null || firstName.trim().isEmpty()) errorMessages.append("First name is required. ");
            if (lastName == null || lastName.trim().isEmpty()) errorMessages.append("Last name is required. ");
            if (email == null || email.trim().isEmpty()) {
                errorMessages.append("Email is required. ");
            } else if (!isValidEmail(email)) {
                errorMessages.append("Invalid email format. ");
            }
            if (reenterEmail == null || !email.equals(reenterEmail)) {
                errorMessages.append("Email addresses do not match. ");
            }
            if (password == null || password.length() < 6) {
                errorMessages.append("Password must be at least 6 characters long. ");
            }
            if (gender == null || gender.trim().isEmpty()) errorMessages.append("Gender is required. ");

            // Parse birthday
            LocalDate birthday = null;
            if (birthdayStr != null && !birthdayStr.trim().isEmpty()) {
                try {
                    birthday = LocalDate.parse(birthdayStr);
                } catch (DateTimeParseException e) {
                    errorMessages.append("Invalid birthday format. ");
                }
            } else {
                errorMessages.append("Birthday is required. ");
            }

            // Nếu có lỗi -> quay lại form
            if (errorMessages.length() > 0) {
                request.setAttribute("errorMessage", errorMessages.toString());
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("email", email);
                request.setAttribute("gender", gender);

                RequestDispatcher dispatcher = request.getRequestDispatcher("user-registration.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Tạo User object
            User user = new User(
                    firstName.trim(),
                    lastName.trim(),
                    email.trim().toLowerCase(),
                    password,
                    birthday,
                    gender
            );

            // Lưu xuống database qua DAO
            boolean saved = UserDAO.save(user);

            if (saved) {
                request.setAttribute("successMessage", "Registration successful!");
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("registration-success.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Could not save user. Email may already exist.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-registration.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-registration.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}