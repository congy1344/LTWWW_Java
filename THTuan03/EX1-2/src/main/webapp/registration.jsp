<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Student Registration</title>
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
        }
        .form-group {
            margin: 12px 0;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 6px;
        }
        input[type=text], input[type=email], input[type=date], textarea, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
            height: 70px;
        }
        .inline {
            display: inline-block;
            margin-right: 20px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
        }
        .btn-submit {
            background: #007acc;
            color: white;
        }
        .btn-reset {
            background: #ccc;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Student Registration Form</h2>
    <form action="registration-form" method="post">
        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstName" maxlength="30">
        </div>
        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" maxlength="30">
        </div>
        <div class="form-group">
            <label>Date of Birth:</label>
            <input type="date" name="dob">
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email">
        </div>
        <div class="form-group">
            <label>Mobile Number:</label>
            <input type="text" name="mobile" maxlength="10">
        </div>
        <div class="form-group">
            <label>Gender:</label>
            <label class="inline"><input type="radio" name="gender" value="Male"> Male</label>
            <label class="inline"><input type="radio" name="gender" value="Female"> Female</label>
        </div>
        <div class="form-group">
            <label>Address:</label>
            <textarea name="address"></textarea>
        </div>
        <div class="form-group">
            <label>City:</label>
            <input type="text" name="city">
        </div>
        <div class="form-group">
            <label>Pin Code:</label>
            <input type="text" name="pin" maxlength="6">
        </div>
        <div class="form-group">
            <label>State:</label>
            <input type="text" name="state">
        </div>
        <div class="form-group">
            <label>Country:</label>
            <input type="text" name="country" value="India">
        </div>
        <div class="form-group">
            <label>Hobbies:</label>
            <label class="inline"><input type="checkbox" name="hobbies" value="Drawing"> Drawing</label>
            <label class="inline"><input type="checkbox" name="hobbies" value="Singing"> Singing</label>
            <label class="inline"><input type="checkbox" name="hobbies" value="Dancing"> Dancing</label>
            <label class="inline"><input type="checkbox" name="hobbies" value="Sketching"> Sketching</label>
            <label class="inline">Others: <input type="text" name="otherHobby"></label>
        </div>
        <div class="form-group">
            <label>Qualification:</label>
            <table border="1" cellpadding="6" cellspacing="0" style="border-collapse: collapse; width:100%;">
                <tr style="background:#d9f2ff;">
                    <th>Sl.No.</th>
                    <th>Examination</th>
                    <th>Board</th>
                    <th>Percentage</th>
                    <th>Year of Passing</th>
                </tr>
                <tr>
                    <td>1</td><td>Class X</td>
                    <td><input type="text" name="board1" maxlength="10"></td>
                    <td><input type="text" name="percent1"></td>
                    <td><input type="text" name="year1"></td>
                </tr>
                <tr>
                    <td>2</td><td>Class XII</td>
                    <td><input type="text" name="board2" maxlength="10"></td>
                    <td><input type="text" name="percent2"></td>
                    <td><input type="text" name="year2"></td>
                </tr>
                <tr>
                    <td>3</td><td>Graduation</td>
                    <td><input type="text" name="board3" maxlength="10"></td>
                    <td><input type="text" name="percent3"></td>
                    <td><input type="text" name="year3"></td>
                </tr>
                <tr>
                    <td>4</td><td>Masters</td>
                    <td><input type="text" name="board4" maxlength="10"></td>
                    <td><input type="text" name="percent4"></td>
                    <td><input type="text" name="year4"></td>
                </tr>
            </table>
        </div>

        <div class="form-group">
            <label>Course applies for:</label>
            <label class="inline"><input type="radio" name="course" value="BCA"> BCA</label>
            <label class="inline"><input type="radio" name="course" value="B.Com"> B.Com</label>
            <label class="inline"><input type="radio" name="course" value="B.Sc"> B.Sc</label>
            <label class="inline"><input type="radio" name="course" value="B.A"> B.A</label>
        </div>
        <div class="form-group" style="text-align:center;">
            <button type="submit" class="btn btn-submit">Submit</button>
            <button type="reset" class="btn btn-reset">Reset</button>
        </div>
    </form>
</div>
</body>
</html>
