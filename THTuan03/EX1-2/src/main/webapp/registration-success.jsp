<%@ page import="fit.se.thtuan03.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
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
            border-radius: 15px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.2);
            max-width: 600px;
            width: 90%;
            text-align: center;
        }

        .success-icon {
            font-size: 80px;
            color: #27ae60;
            margin-bottom: 20px;
        }

        .success-title {
            color: #27ae60;
            font-size: 32px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .success-message {
            color: #666;
            font-size: 18px;
            margin-bottom: 30px;
        }

        .user-details {
            background: #f8f9fa;
            padding: 25px;
            border-radius: 10px;
            margin: 20px 0;
            text-align: left;
        }

        .user-details h3 {
            color: #333;
            margin-top: 0;
            margin-bottom: 20px;
            text-align: center;
            font-size: 24px;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .detail-row:last-child {
            border-bottom: none;
        }

        .detail-label {
            font-weight: bold;
            color: #555;
            font-size: 16px;
        }

        .detail-value {
            color: #333;
            font-size: 16px;
        }

        .btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 10px;
            transition: transform 0.2s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
            text-decoration: none;
            color: white;
        }

        .btn-secondary {
            background: linear-gradient(135deg, #95a5a6 0%, #7f8c8d 100%);
        }

        .welcome-text {
            color: #333;
            font-size: 18px;
            margin-bottom: 15px;
        }

        @media (max-width: 600px) {
            .success-container {
                padding: 20px;
            }

            .success-title {
                font-size: 24px;
            }

            .detail-row {
                flex-direction: column;
                align-items: flex-start;
                gap: 5px;
            }
        }
    </style>
</head>
<body>
<div class="success-container">
    <%
        User user = (User) request.getAttribute("user");
        String successMessage = (String) request.getAttribute("successMessage");

        if (user != null) {
    %>

    <div class="success-icon">✅</div>
    <div class="success-title">Registration Successful!</div>
    <div class="success-message">
        <%= successMessage != null ? successMessage : "Welcome to our platform!" %>
    </div>

    <div class="welcome-text">
        Welcome, <strong><%= user.getFirstName() %> <%= user.getLastName() %></strong>!
    </div>

    <div class="user-details">
        <h3>Your Registration Details</h3>

        <div class="detail-row">
            <span class="detail-label">Full Name:</span>
            <span class="detail-value"><%= user.getFirstName() %> <%= user.getLastName() %></span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Email Address:</span>
            <span class="detail-value"><%= user.getEmail() %></span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Gender:</span>
            <span class="detail-value"><%= user.getGender() %></span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Birthday:</span>
            <span class="detail-value">
                    <%= user.getBirthday() != null ? user.getBirthday().toString() : "Not specified" %>
                </span>
        </div>

        <div class="detail-row">
            <span class="detail-label">Registration Date:</span>
            <span class="detail-value"><%= new java.text.SimpleDateFormat("MMMM dd, yyyy 'at' HH:mm").format(new java.util.Date()) %></span>
        </div>
    </div>

    <div style="margin-top: 30px;">
        <p style="color: #666; margin-bottom: 20px;">
            Thank you for registering with us! You can now start using our services.
        </p>

        <a href="user-registration.jsp" class="btn btn-secondary">Register Another User</a>
        <a href="#" class="btn" onclick="alert('Login functionality will be implemented next!')">Login Now</a>
    </div>

    <%
    } else {
    %>

    <div class="success-icon">❌</div>
    <div class="success-title" style="color: #e74c3c;">Registration Failed</div>
    <div class="success-message">
        Sorry, there was an error processing your registration. Please try again.
    </div>

    <div style="margin-top: 30px;">
        <a href="user-registration.jsp" class="btn">Try Again</a>
    </div>

    <%
        }
    %>
</div>
</body>
</html>