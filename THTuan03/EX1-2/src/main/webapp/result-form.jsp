<%@ page import="fit.se.thtuan03.model.Student" %>
<%@ page import="fit.se.thtuan03.model.Student.Qualification" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #e6f7ff;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 70%;
            margin: 30px auto;
            background: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #007acc;
            margin-bottom: 30px;
        }
        .info-section {
            margin: 20px 0;
        }
        .info-row {
            margin: 8px 0;
            padding: 5px;
        }
        .label {
            font-weight: bold;
            display: inline-block;
            width: 150px;
            color: #333;
        }
        .value {
            color: #666;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 15px 0;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background: #d9f2ff;
            font-weight: bold;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
            background: #007acc;
            color: white;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .btn:hover {
            background: #005fa3;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        Student student = (Student) request.getAttribute("student");
        if (student != null) {
    %>
    <h2>Student Registration Successful</h2>

    <div class="info-section">
        <h3 style="color: #007acc; border-bottom: 2px solid #007acc; padding-bottom: 5px;">Personal Information</h3>
        <div class="info-row">
            <span class="label">First Name:</span>
            <span class="value"><%= student.getFirstName() != null ? student.getFirstName() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Last Name:</span>
            <span class="value"><%= student.getLastName() != null ? student.getLastName() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Date of Birth:</span>
            <span class="value"><%= student.getDob() != null ? student.getDob().toString() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Email:</span>
            <span class="value"><%= student.getEmail() != null ? student.getEmail() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Mobile:</span>
            <span class="value"><%= student.getMobile() != null ? student.getMobile() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Gender:</span>
            <span class="value"><%= student.getGender() != null ? student.getGender() : "Not provided" %></span>
        </div>
    </div>

    <div class="info-section">
        <h3 style="color: #007acc; border-bottom: 2px solid #007acc; padding-bottom: 5px;">Address Information</h3>
        <div class="info-row">
            <span class="label">Address:</span>
            <span class="value"><%= student.getAddress() != null ? student.getAddress() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">City:</span>
            <span class="value"><%= student.getCity() != null ? student.getCity() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Pin Code:</span>
            <span class="value"><%= student.getPin() != null ? student.getPin() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">State:</span>
            <span class="value"><%= student.getState() != null ? student.getState() : "Not provided" %></span>
        </div>
        <div class="info-row">
            <span class="label">Country:</span>
            <span class="value"><%= student.getCountry() != null ? student.getCountry() : "Not provided" %></span>
        </div>
    </div>

    <div class="info-section">
        <h3 style="color: #007acc; border-bottom: 2px solid #007acc; padding-bottom: 5px;">Hobbies</h3>
        <div class="info-row">
            <span class="label">Selected Hobbies:</span>
            <span class="value">
                <%
                    if (student.getHobbies() != null && !student.getHobbies().isEmpty()) {
                        StringBuilder hobbiesStr = new StringBuilder();
                        for (int i = 0; i < student.getHobbies().size(); i++) {
                            hobbiesStr.append(student.getHobbies().get(i));
                            if (i < student.getHobbies().size() - 1) {
                                hobbiesStr.append(", ");
                            }
                        }
                %>
                <%= hobbiesStr.toString() %>
                <%
                } else {
                %>
                None selected
                <%
                    }
                %>
            </span>
        </div>
    </div>

    <div class="info-section">
        <h3 style="color: #007acc; border-bottom: 2px solid #007acc; padding-bottom: 5px;">Educational Qualifications</h3>
        <table>
            <thead>
            <tr>
                <th>Sl.No.</th>
                <th>Examination</th>
                <th>Board</th>
                <th>Percentage</th>
                <th>Year of Passing</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (student.getQualifications() != null && !student.getQualifications().isEmpty()) {
                    int count = 1;
                    for (Qualification qual : student.getQualifications()) {
            %>
            <tr>
                <td><%= count++ %></td>
                <td><%= qual.getExam() %></td>
                <td><%= qual.getBoard() != null && !qual.getBoard().isEmpty() ? qual.getBoard() : "-" %></td>
                <td><%= qual.getPercentage() != null && !qual.getPercentage().isEmpty() ? qual.getPercentage() : "-" %></td>
                <td><%= qual.getYearOfPassing() != null && !qual.getYearOfPassing().isEmpty() ? qual.getYearOfPassing() : "-" %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" style="text-align: center;">No qualifications provided</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="info-section">
        <h3 style="color: #007acc; border-bottom: 2px solid #007acc; padding-bottom: 5px;">Course Information</h3>
        <div class="info-row">
            <span class="label">Course Applied For:</span>
            <span class="value"><%= student.getCourse() != null ? student.getCourse() : "Not selected" %></span>
        </div>
    </div>

    <%
    } else {
    %>
    <h2 style="color: #cc0000;">Error: No student data found!</h2>
    <p>There was an error processing your registration. Please try again.</p>
    <%
        }
    %>

    <div class="center">
        <a href="student-registration.jsp" class="btn">Register Another Student</a>
    </div>
</div>
</body>
</html>