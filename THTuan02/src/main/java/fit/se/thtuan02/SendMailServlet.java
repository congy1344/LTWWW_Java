package fit.se.thtuan02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.util.Properties;

@WebServlet("/sendMail")
public class SendMailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String to = req.getParameter("to");
        String subject = req.getParameter("subject");
        String messageText = req.getParameter("message");

        // Cấu hình mail server (ví dụ Gmail SMTP)
        String host = "smtp.gmail.com";
        final String user = "your-email@gmail.com";     // Gmail của bạn
        final String password = "your-app-password";    // App Password (không dùng mật khẩu Gmail thường)

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        String result;
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject, "UTF-8");
            message.setText(messageText, "UTF-8");

            Transport.send(message);
            result = "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3>" + result + "</h3>");
    }
}
