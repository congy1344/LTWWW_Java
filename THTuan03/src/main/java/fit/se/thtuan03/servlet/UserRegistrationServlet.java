package fit.se.thtuan03.servlet;

import fit.se.thtuan03.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        // Redirect to registration form
        response.sendRedirect("user-registration.jsp");
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
            
            // Validate data
            StringBuilder errorMessages = new StringBuilder();
            
            // Validate required fields
            if (firstName == null || firstName.trim().isEmpty()) {
                errorMessages.append("First name is required. ");
            }
            
            if (lastName == null || lastName.trim().isEmpty()) {
                errorMessages.append("Last name is required. ");
            }
            
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
            
            if (gender == null || gender.trim().isEmpty()) {
                errorMessages.append("Gender is required. ");
            }
            
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
            
            // If there are validation errors, redirect back to form
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
            
            // Create User object
            User user = new User(
                firstName.trim(),
                lastName.trim(),
                email.trim().toLowerCase(),
                password, // In real application, you should hash the password
                birthday,
                gender
            );
            
            // Store user in session or database (for demo, we'll use request attribute)
            request.setAttribute("user", user);
            request.setAttribute("successMessage", "Registration successful!");
            
            // Forward to success page
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration-success.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            // Handle any unexpected errors
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-registration.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    /**
     * Simple email validation method
     */
    private boolean isValidEmail(String email) {
        return email != null && 
               email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}