package fit.se.thtuan02.uploadfile;

import fit.se.thtuan02.dao.ContactDAO;
import fit.se.thtuan02.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private ContactDAO contactDAO = new ContactDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Part filePart = req.getPart("photo");

        byte[] photoBytes = null;
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                photoBytes = inputStream.readAllBytes();
            }
        }

        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoto(photoBytes);

        String message;
        try {
            contactDAO.insert(contact);
            message = "Upload thành công!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Upload thất bại: " + e.getMessage();
        }

        // In thẳng ra response, không cần JSP
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>" + message + "</h2>");
            out.println("<a href='upload.html'>Quay lại form</a>");
            out.println("</body></html>");
        }
    }
}
