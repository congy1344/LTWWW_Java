package fit.se.thtuan03.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import fit.se.thtuan03.model.Student;

@WebServlet("/registration-form")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin cơ bản
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        LocalDate dob = null;
        String dobParam = request.getParameter("dob");
        if (dobParam != null && !dobParam.isEmpty()) {
            dob = LocalDate.parse(dobParam); // yyyy-MM-dd
        }

        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pin = request.getParameter("pin");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        // Hobbies
        List<String> hobbies = new ArrayList<>();
        String[] selectedHobbies = request.getParameterValues("hobbies");
        if (selectedHobbies != null) hobbies.addAll(Arrays.asList(selectedHobbies));
        String other = request.getParameter("otherHobby");
        if (other != null && !other.isEmpty()) hobbies.add(other);

        // Qualifications
        List<Student.Qualification> qualifications = new ArrayList<>();
        qualifications.add(new Student.Qualification("Class X",
                request.getParameter("board1"),
                request.getParameter("percent1"),
                request.getParameter("year1")));
        qualifications.add(new Student.Qualification("Class XII",
                request.getParameter("board2"),
                request.getParameter("percent2"),
                request.getParameter("year2")));
        qualifications.add(new Student.Qualification("Graduation",
                request.getParameter("board3"),
                request.getParameter("percent3"),
                request.getParameter("year3")));
        qualifications.add(new Student.Qualification("Masters",
                request.getParameter("board4"),
                request.getParameter("percent4"),
                request.getParameter("year4")));

        String course = request.getParameter("course");

        // Tạo Student object
        Student st = new Student(firstName, lastName, dob, email, mobile, gender,
                address, city, pin, state, country, hobbies, qualifications, course);

        // Gửi sang JSP
        request.setAttribute("student", st);
        RequestDispatcher rd = request.getRequestDispatcher("result-form.jsp");
        rd.forward(request, response);
    }
}
