<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết Quả</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .result-container {
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg form-header">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/tin-tuc">
            <i class="fas fa-newspaper me-2"></i>Quản Lý Tin Tức
        </a>
        <div class="navbar-nav ms-auto">
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/tin-tuc">
                <i class="fas fa-list me-1"></i>Danh Sách Tin Tức
            </a>
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/them-tin-tuc">
                <i class="fas fa-plus-circle me-1"></i>Thêm Tin Tức
            </a>
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/quan-ly-tin-tuc">
                <i class="fas fa-cog me-1"></i>Quản Lý
            </a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="result-container">
        <div class="card shadow">
            <div class="card-header form-header">
                <h5 class="mb-0">
                    <i class="fas fa-info-circle me-2"></i>Kết quả
                </h5>
            </div>
            <div class="card-body text-center">
                <c:choose>
                    <c:when test="${messageType == 'success'}">
                        <div class="alert alert-success">
                            <i class="fas fa-check-circle me-2"></i>${message}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-danger">
                            <i class="fas fa-exclamation-triangle me-2"></i>${message}
                        </div>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/tin-tuc" class="btn btn-primary mt-3">
                    <i class="fas fa-arrow-left me-1"></i>Quay lại danh sách
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
