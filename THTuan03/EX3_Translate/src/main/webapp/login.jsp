<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="login"/></title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Arial, sans-serif;
      background: linear-gradient(135deg, #667eea, #764ba2);
      display: flex;
      flex-direction: column;
      align-items: center;
      padding-top: 60px;
      min-height: 100vh;
    }

    .form-container {
      background-color: #ffffff;
      border-radius: 12px;
      padding: 25px 30px;
      margin-bottom: 25px;
      box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
      width: 380px;
      transition: transform 0.2s ease-in-out;
    }

    .form-container:hover {
      transform: translateY(-3px);
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 20px;
      font-size: 22px;
    }

    form {
      display: flex;
      flex-direction: column;
      gap: 12px;
    }

    .form-row {
      display: flex;
      flex-direction: column;
    }

    .form-row label {
      font-weight: 500;
      margin-bottom: 5px;
      color: #444;
    }

    .form-row input[type="text"],
    .form-row input[type="password"] {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
      outline: none;
      transition: border-color 0.2s;
    }

    .form-row input:focus {
      border-color: #667eea;
    }

    .lang-options {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 15px;
    }

    .lang-options label {
      display: flex;
      align-items: center;
      gap: 5px;
      font-size: 14px;
    }

    input[type="submit"] {
      padding: 10px;
      border: none;
      background: linear-gradient(90deg, #667eea, #764ba2);
      color: white;
      cursor: pointer;
      border-radius: 6px;
      font-size: 15px;
      font-weight: 600;
      transition: background 0.3s;
    }

    input[type="submit"]:hover {
      background: linear-gradient(90deg, #5a6fd6, #6a409a);
    }
  </style>
</head>
<body>

<div class="form-container">
  <c:set var="languageCode" value="${param.radLanguageCode}"/>
  <c:if test="${empty languageCode}">
    <c:set var="languageCode" value="en"/>
  </c:if>

  <fmt:setLocale value="${languageCode}" scope="session"/>
  <fmt:setBundle basename="resource" scope="session"/>

  <h2><fmt:message key="languageMessage"/></h2>
  <form action="login.jsp" method="POST" style="align-items: center;">
    <div class="lang-options">
      <label>
        <input type="radio" name="radLanguageCode" value="vi"
                <c:if test="${languageCode == 'vi'}"> checked</c:if> />
        <fmt:message key="vn"/>
      </label>
      <label>
        <input type="radio" name="radLanguageCode" value="en"
                <c:if test="${languageCode == 'en'}"> checked</c:if> />
        <fmt:message key="en"/>
      </label>
    </div>
    <input type="submit" name="submit" value="<fmt:message key='chooseButton' />"/>
  </form>
</div>

<div class="form-container">
  <h2><fmt:message key="login"/></h2>
  <form action="login.jsp" method="POST">
    <div class="form-row">
      <label><fmt:message key="userName"/></label>
      <input type="text" name="txtUserName" value=""/>
    </div>
    <div class="form-row">
      <label><fmt:message key="pass"/></label>
      <input type="password" name="txtPassword" value=""/>
    </div>
    <input type="submit" name="submit" value="<fmt:message key='login' />"/>
  </form>
</div>

</body>
</html>
