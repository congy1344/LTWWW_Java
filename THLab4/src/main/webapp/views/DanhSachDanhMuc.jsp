<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Danh Mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .table-container {
            max-width: 800px;
            margin: 0 auto;
        }
    </style>
</head>
<body class="bg-light">
<!-- Navigation -->
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
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/quan-ly-danh-muc">
                <i class="fas fa-folder-plus me-1"></i>Thêm Danh Mục
            </a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="table-container">
        <!-- Page Header -->
        <div class="row mb-4">
            <div class="col">
                <h2 class="text-center text-primary">
                    <i class="fas fa-folder me-2"></i>Danh Sách Danh Mục
                </h2>
            </div>
        </div>

        <!-- Category List -->
        <div class="card shadow">
            <div class="card-header form-header">
                <h5 class="mb-0">
                    <i class="fas fa-list-ul me-2"></i>Danh mục
                </h5>
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Mã danh mục</th>
                        <th scope="col">Tên danh mục</th>
                        <th scope="col">Người quản lý</th>
                        <th scope="col">Ghi chú</th>
                        <th scope="col">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="danhMuc" items="${listDanhMuc}">
                        <tr>
                            <td>${danhMuc.maDM}</td>
                            <td>${danhMuc.tenDanhMuc}</td>
                            <td>${danhMuc.nguoiQuanLy}</td>
                            <td>${danhMuc.ghiChu}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/quan-ly-danh-muc?maDM=${danhMuc.maDM}"
                                   class="btn btn-sm btn-primary">
                                    <i class="fas fa-edit"></i> Sửa
                                </a>
                                <a href="${pageContext.request.contextPath}/tin-tuc?action=delete&maDM=${danhMuc.maDM}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Bạn có chắc muốn xóa danh mục này?')">
                                    <i class="fas fa-trash"></i> Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty listDanhMuc}">
                    <div class="alert alert-info text-center">
                        <i class="fas fa-info-circle me-2"></i>Chưa có danh mục nào.
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
