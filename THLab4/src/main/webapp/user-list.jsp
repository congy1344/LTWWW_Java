<%--
  Created by IntelliJ IDEA.
  User: CongY
  Date: 9/25/2025
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="fit.se.thlab4.model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            margin: 0;
            padding: 20px;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .success-container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            width: 500px;
            max-width: 90%;
            text-align: center;
        }

        .success-icon {
            color: #28a745;
            font-size: 60px;
            margin-bottom: 20px;
        }

        .success-title {
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
        }

        .success-message {
            color: #28a745;
            font-size: 18px;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .user-info {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            border-left: 4px solid #28a745;
        }

        .user-info h3 {
            margin-top: 0;
            color: #333;
            margin-bottom: 15px;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            padding: 8px 0;
            border-bottom: 1px solid #dee2e6;
        }

        .info-row:last-child {
            border-bottom: none;
            margin-bottom: 0;
        }

        .info-label {
            font-weight: bold;
            color: #555;
        }

        .info-value {
            color: #333;
        }

        .btn-container {
            margin-top: 30px;
        }

        .btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.2s ease;
            margin: 0 5px;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
        }

        .gender-badge {
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
        }

        .gender-male {
            background-color: #cce5ff;
            color: #0066cc;
        }

        .gender-female {
            background-color: #ffe6f2;
            color: #cc0066;
        }
    </style>
</head>
<body>
<div class="success-container">
    <div class="success-icon">✓</div>
    <div class="success-title">Registration Successful!</div>

    <%
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) {
    %>
    <div class="success-message">
        <%= successMessage %>
    </div>
    <%
        }
    %>

    <%
        User user = (User) request.getAttribute("user");
        if (user != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    %>
    <div class="user-info">
        <h3>Your Registration Details:</h3>

        <div class="info-row">
            <span class="info-label">Full Name:</span>
            <span class="info-value"><%= user.getFirstName() %> <%= user.getLastName() %></span>
        </div>

        <div class="info-row">
            <span class="info-label">Email:</span>
            <span class="info-value"><%= user.getEmail() %></span>
        </div>

        <div class="info-row">
            <span class="info-label">Birthday:</span>
            <span class="info-value">
                        <%= user.getBirthday() != null ? user.getBirthday().format(formatter) : "N/A" %>
                    </span>
        </div>

        <div class="info-row">
            <span class="info-label">Gender:</span>
            <span class="info-value">
                        <span class="gender-badge <%= "Male".equals(user.getGender()) ? "gender-male" : "gender-female" %>">
                            <%= user.getGender() %>
                        </span>
                    </span>
        </div>
    </div>
    <%
        }
    %>

    <div class="btn-container">
        <a href="user-list" class="btn btn-secondary">View All Users</a>
        <a href="user-registration" class="btn">Register Another User</a>
    </div>
</div>
</body>
</html>
