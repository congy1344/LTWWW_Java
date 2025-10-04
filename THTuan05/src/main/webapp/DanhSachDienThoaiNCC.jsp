<%@ include file="Header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>Danh Sách Điện Thoại Theo Nhà Cung Cấp</h1>

<form action="${pageContext.request.contextPath}/danh-sach-san-pham" method="get" style="margin-bottom: 20px;">
    <input type="text" name="timKiemNcc" placeholder="Tìm NCC (Tên, ĐC, SĐT)" style="padding: 8px; width: 300px;">
    <button type="submit" style="padding: 8px 15px;">Tìm Kiếm NCC</button>
</form>

<div style="display: flex;">
    <div style="width: 30%; border-right: 1px solid #ccc; padding-right: 20px;">
        <h3>Nhà Cung Cấp</h3>
        <ul>
            <c:forEach var="ncc" items="${dsNhaCungCap}">
                <li>
                    <a href="${pageContext.request.contextPath}/danh-sach-san-pham?maNcc=${ncc.maNCC}">
                            ${ncc.tenNhaCC} (${ncc.maNCC})
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div style="width: 70%; padding-left: 20px;">
        <c:choose>
            <c:when test="${nccHienTai != null}">
                <h3>Điện Thoại của ${nccHienTai.tenNhaCC}</h3>
                <c:if test="${not empty dsDienThoai}">
                    <table>
                        <thead>
                        <tr>
                            <th>Ảnh</th>
                            <th>Mã ĐT</th>
                            <th>Tên ĐT</th>
                            <th>Năm SX</th>
                            <th>Cấu hình</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dt" items="${dsDienThoai}">
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/images/products/${dt.hinhAnh}"
                                         style="width: 50px; height: 50px; object-fit: cover;">
                                </td>
                                <td>${dt.maDT}</td>
                                <td>${dt.tenDT}</td>
                                <td>${dt.namSanXuat}</td>
                                <td>${dt.cauHinh}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty dsDienThoai}">
                    <p>Không tìm thấy sản phẩm nào cho nhà cung cấp này.</p>
                </c:if>
            </c:when>
            <c:otherwise>
                <p>Vui lòng chọn một Nhà Cung Cấp hoặc thực hiện tìm kiếm.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@ include file="Footer.jsp" %>
