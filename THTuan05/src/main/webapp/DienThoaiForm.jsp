<%@ include file="Header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>Thêm Mới Sản Phẩm Điện Thoại</h1>

<c:if test="${not empty error}">
    <p style="color: red; font-weight: bold;">Lỗi: ${error}</p>
</c:if>

<form action="${pageContext.request.contextPath}/them-moi-san-pham" method="post" enctype="multipart/form-data"
      onsubmit="return validateForm()">
    <div class="form-group">
        <label for="tenDt">Tên Điện Thoại (*):</label>
        <input type="text" id="tenDt" name="tenDt" required>
    </div>

    <div class="form-group">
        <label for="namSanXuat">Năm Sản Xuất (YYYY) (*):</label>
        <input type="text" id="namSanXuat" name="namSanXuat" required pattern="^\d{4}$"
               title="Năm sản xuất phải là 4 chữ số (ví dụ: 2024)">
    </div>

    <div class="form-group">
        <label for="cauHinh">Cấu hình (tối đa 255 ký tự) (*):</label>
        <input type="text" id="cauHinh" name="cauHinh" required maxlength="255">
    </div>

    <div class="form-group">
        <label for="maNcc">Nhà Cung Cấp (*):</label>
        <select id="maNcc" name="maNcc" required>
            <option value="">-- Chọn Nhà Cung Cấp --</option>
            <c:forEach var="ncc" items="${dsNhaCungCap}">
                <option value="${ncc.maNCC}">${ncc.tenNhaCC}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="hinhAnh">Hình ảnh (png, jpg, jpeg) (*):</label>
        <input type="file" id="hinhAnh" name="hinhAnh" accept=".png, .jpg, .jpeg" required>
        <p id="fileError" style="color: red;"></p>
    </div>

    <button type="submit"
            style="padding: 10px 20px; background-color: #5cb85c; color: white; border: none; border-radius: 4px; cursor: pointer;">
        Thêm
    </button>
</form>

<script>
    function validateForm() {
        const hinhAnh = document.getElementById('hinhAnh').value;
        const fileError = document.getElementById('fileError');
        const allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;

        if (!allowedExtensions.exec(hinhAnh)) {
            fileError.textContent = 'Chỉ chấp nhận file có đuôi: png, jpg, jpeg.';
            return false;
        } else {
            fileError.textContent = '';
            return true;
        }
    }
</script>

<%@ include file="Footer.jsp" %>
