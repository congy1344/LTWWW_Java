<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Tin Tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .table-container {
            max-width: 1000px;
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
    <div class="table-container">
        <div class="row mb-4">
            <div class="col">
                <h2 class="text-center text-primary">
                    <i class="fas fa-newspaper me-2"></i>Danh Sách Tin Tức
                </h2>
            </div>
        </div>

        <div class="card shadow mb-4">
            <div class="card-header form-header">
                <h5 class="mb-0">
                    <i class="fas fa-filter me-2"></i>Lọc theo danh mục
                </h5>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/tin-tuc" method="get">
                    <input type="hidden" name="action" value="category">
                    <div class="input-group">
                        <select class="form-select" name="categoryId" required>
                            <option value="0" ${selectedCategory == 0 ? 'selected' : ''}>Tất cả danh mục</option>
                            <!-- Giả sử danh mục được lấy từ CSDL -->
                            <c:forEach var="category" items="${applicationScope.categories}">
                                <option value="${category.maDM}" ${selectedCategory == category.maDM ? 'selected' : ''}>
                                        ${category.tenDanhMuc}
                                </option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-search me-1"></i>Lọc
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow">
            <div class="card-header form-header">
                <h5 class="mb-0">
                    <i class="fas fa-list-ul me-2"></i>Danh sách tin tức
                </h5>
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Mã tin tức</th>
                        <th scope="col">Tiêu đề</th>
                        <th scope="col">Tóm tắt</th>
                        <th scope="col">Danh mục</th>
                        <th scope="col">Liên kết</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tinTuc" items="${listTinTuc}">
                        <tr>
                            <td>${tinTuc.maTT}</td>
                            <td>${tinTuc.tieuDe}</td>
                            <td>${tinTuc.noiDungTT}</td>
                            <td>${tinTuc.danhMuc.tenDanhMuc}</td>
                            <td>
                                <c:if test="${not empty tinTuc.lienKet}">
                                    <a href="${tinTuc.lienKet}" target="_blank">Xem</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty listTinTuc}">
                    <div class="alert alert-info text-center">
                        <i class="fas fa-info-circle me-2"></i>Chưa có tin tức nào.
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
