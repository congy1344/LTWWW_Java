<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration Form</title>
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

        .form-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            width: 400px;
            max-width: 90%;
        }

        .form-title {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-size: 24px;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus {
            border-color: #667eea;
            outline: none;
        }

        .gender-group {
            display: flex;
            gap: 20px;
            margin-top: 8px;
        }

        .gender-option {
            display: flex;
            align-items: center;
            gap: 8px;
        }

        input[type="radio"] {
            margin: 0;
        }

        .btn-container {
            text-align: center;
            margin-top: 30px;
        }

        .btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .birthday-group {
            display: flex;
            gap: 10px;
            align-items: end;
        }

        .birthday-group select {
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            flex: 1;
        }
    </style>
    <script>
        window.onload = function() {
            // Generate days
            const daySelect = document.getElementById('day');
            for (let i = 1; i <= 31; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.text = i;
                daySelect.appendChild(option);
            }

            // Generate months
            const monthSelect = document.getElementById('month');
            const months = ['January', 'February', 'March', 'April', 'May', 'June',
                'July', 'August', 'September', 'October', 'November', 'December'];
            for (let i = 0; i < months.length; i++) {
                const option = document.createElement('option');
                option.value = i + 1;
                option.text = months[i];
                monthSelect.appendChild(option);
            }

            // Generate years
            const yearSelect = document.getElementById('year');
            const currentYear = new Date().getFullYear();
            for (let i = currentYear; i >= 1950; i--) {
                const option = document.createElement('option');
                option.value = i;
                option.text = i;
                yearSelect.appendChild(option);
            }
        }

        // Gộp ngày/tháng/năm thành 1 hidden input trước khi submit
        function setBirthday() {
            const day = document.getElementById('day').value;
            const month = document.getElementById('month').value;
            const year = document.getElementById('year').value;
            if (day && month && year) {
                document.getElementById('hiddenBirthday').value =
                    year + '-' + String(month).padStart(2, '0') + '-' + String(day).padStart(2, '0');
            }
            return true; // cho phép submit
        }
    </script>
</head>
<body>
<div class="form-container">
    <div class="form-title">User Registration Form</div>

    <form action="user-registration" method="post" onsubmit="return setBirthday()">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" maxlength="50">
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" maxlength="50">
        </div>

        <div class="form-group">
            <label for="email">Your Email:</label>
            <input type="email" id="email" name="email" maxlength="100">
        </div>

        <div class="form-group">
            <label for="reenterEmail">Re-enter Email:</label>
            <input type="email" id="reenterEmail" name="reenterEmail" maxlength="100">
        </div>

        <div class="form-group">
            <label for="password">New Password:</label>
            <input type="password" id="password" name="password" maxlength="50">
        </div>

        <div class="form-group">
            <label>Birthday:</label>
            <div class="birthday-group">
                <select id="day" name="day">
                    <option value="">Day</option>
                </select>
                <select id="month" name="month">
                    <option value="">Month</option>
                </select>
                <select id="year" name="year">
                    <option value="">Year</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label>Gender:</label>
            <div class="gender-group">
                <div class="gender-option">
                    <input type="radio" id="female" name="gender" value="Female">
                    <label for="female">Female</label>
                </div>
                <div class="gender-option">
                    <input type="radio" id="male" name="gender" value="Male">
                    <label for="male">Male</label>
                </div>
            </div>
        </div>

        <!-- Hidden field cho ngày sinh format -->
        <input type="hidden" id="hiddenBirthday" name="birthday">

        <div class="btn-container">
            <button type="submit" class="btn">Sign Up</button>
        </div>
    </form>
</div>
</body>
</html>
