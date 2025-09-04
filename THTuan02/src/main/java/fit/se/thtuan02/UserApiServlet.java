package fit.se.thtuan02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fit.se.thtuan02.dao.UserDAO;
import fit.se.thtuan02.model.UserForEx5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "UserApiServlet", urlPatterns = {"/api/users/*"})
public class UserApiServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String pathInfo = req.getPathInfo(); // / or /{id}
            if (pathInfo == null || pathInfo.equals("/")) {
                // GET /api/users → list all
                List<UserForEx5> users = userDAO.getAll();
                out.print(gson.toJson(users));
            } else {
                // GET /api/users/{id}
                String[] parts = pathInfo.split("/");
                if (parts.length > 1) {
                    int id = Integer.parseInt(parts[1]);
                    UserForEx5 user = userDAO.getById(id);
                    if (user != null) {
                        out.print(gson.toJson(user));
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        out.print("{\"error\":\"User not found\"}");
                    }
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try (BufferedReader reader = req.getReader()) {
            UserForEx5 user = gson.fromJson(reader, UserForEx5.class);
            userDAO.insert(user);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print("{\"message\":\"User created successfully\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String pathInfo = req.getPathInfo(); // /{id}
            if (pathInfo != null && pathInfo.split("/").length > 1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                try (BufferedReader reader = req.getReader()) {
                    UserForEx5 user = gson.fromJson(reader, UserForEx5.class);
                    user.setId(id);
                    userDAO.update(user);
                    out.print("{\"message\":\"User updated successfully\"}");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"User ID required\"}");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String pathInfo = req.getPathInfo(); // /{id}
            if (pathInfo != null && pathInfo.split("/").length > 1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                userDAO.delete(id);
                out.print("{\"message\":\"User deleted successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"User ID required\"}");
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
