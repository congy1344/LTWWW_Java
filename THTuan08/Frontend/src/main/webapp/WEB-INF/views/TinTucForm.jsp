<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Tin Tức Mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .form-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .form-container {
            max-width: 800px;
            margin: 0 auto;
        }
        .required {
            color: #dc3545;
        }
        .error-message {
            color: #dc3545;
            font-size: 0.875em;
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
                <i class="fas fa-list me-1"></i>Danh Sách
            </a>
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/quan-ly-tin-tuc">
                <i class="fas fa-cog me-1"></i>Quản Lý
            </a>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="form-container">
        <div class="row mb-4">
            <div class="col">
                <h2 class="text-center text-primary">
                    <i class="fas fa-plus-circle me-2"></i>Thêm Tin Tức Mới
                </h2>
            </div>
        </div>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger" role="alert">
                <h6 class="alert-heading">
                    <i class="fas fa-exclamation-triangle me-2"></i>Có lỗi xảy ra:
                </h6>
                <ul class="mb-0">
                    <c:forEach var="error" items="${errors}">
                        <li>${error.message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="card shadow">
            <div class="card-header form-header">
                <h5 class="mb-0">
                    <i class="fas fa-edit me-2"></i>Thông tin tin tức
                </h5>
            </div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/them-tin-tuc" novalidate id="newsForm">
                    <div class="mb-3">
                        <label for="tieuDe" class="form-label">
                            <i class="fas fa-heading me-1"></i>Tiêu đề <span class="required">*</span>
                        </label>
                        <input type="text" class="form-control" id="tieuDe" name="tieuDe"
                               value="${tinTuc.tieuDe}" required maxlength="200">
                        <c:if test="${not empty errors}">
                            <c:forEach var="error" items="${errors}">
                                <c:if test="${error.propertyPath.toString() == 'tieuDe'}">
                                    <div class="error-message mt-1">${error.message}</div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="noiDungTT" class="form-label">
                            <i class="fas fa-file-alt me-1"></i>Tóm tắt <span class="required">*</span>
                        </label>
                        <textarea class="form-control" id="noiDungTT" name="noiDungTT" rows="4"
                                  required maxlength="255">${tinTuc.noiDungTT}</textarea>
                        <c:if test="${not empty errors}">
                            <c:forEach var="error" items="${errors}">
                                <c:if test="${error.propertyPath.toString() == 'noiDungTT'}">
                                    <div class="error-message mt-1">${error.message}</div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="lienKet" class="form-label">
                            <i class="fas fa-link me-1"></i>Liên kết <span class="required">*</span>
                        </label>
                        <input type="text" class="form-control" id="lienKet" name="lienKet"
                               value="${tinTuc.lienKet}" required pattern="^http://.*" maxlength="255">
                        <c:if test="${not empty errors}">
                            <c:forEach var="error" items="${errors}">
                                <c:if test="${error.propertyPath.toString() == 'lienKet'}">
                                    <div class="error-message mt-1">${error.message}</div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="maDM" class="form-label">
                            <i class="fas fa-folder me-1"></i>Danh mục <span class="required">*</span>
                        </label>
                        <select class="form-select" id="maDM" name="maDM" required>
                            <option value="">Chọn danh mục</option>
                            <c:forEach var="category" items="${applicationScope.categories}">
                                <option value="${category.maDM}" ${tinTuc.maDM == category.maDM ? 'selected' : ''}>
                                        ${category.tenDanhMuc}
                                </option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty errors}">
                            <c:forEach var="error" items="${errors}">
                                <c:if test="${error.propertyPath.toString() == 'maDM'}">
                                    <div class="error-message mt-1">${error.message}</div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="d-flex justify-content-end gap-2">
                        <a href="${pageContext.request.contextPath}/tin-tuc"
                           class="btn btn-secondary">
                            <i class="fas fa-arrow-left me-1"></i>Hủy
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-1"></i>Thêm
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('newsForm').addEventListener('submit', function(event) {
        let form = this;
        let lienKet = form.lienKet.value;
        let noiDungTT = form.noiDungTT.value;

        // Kiểm tra liên kết
        let lienKetPattern = /^http:\/\/.+/;
        if (!lienKetPattern.test(lienKet)) {
            alert("Liên kết phải bắt đầu bằng 'http://'");
            event.preventDefault();
            return;
        }

        // Kiểm tra nội dung (không quá 255 ký tự)
        if (noiDungTT.length > 255) {
            alert("Nội dung không được vượt quá 255 ký tự");
            event.preventDefault();
            return;
        }
    });
</script>
</body>
</html>
