<%@ include file="Header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>Chức Năng Quản Lý (Xóa Sản Phẩm)</h1>

<c:if test="${not empty successMessage}">
    <p style="color: green; font-weight: bold;">${successMessage}</p>
</c:if>
<c:if test="${not empty errorMessage}">
    <p style="color: red; font-weight: bold;">${errorMessage}</p>
</c:if>

<c:choose>
    <c:when test="${not empty dsDienThoai}">
        <table>
            <thead>
            <tr>
                <th>Mã ĐT</th>
                <th>Tên ĐT</th>
                <th>NCC</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="dt" items="${dsDienThoai}">
                <tr>
                    <td>${dt.maDT}</td>
                    <td>${dt.tenDT}</td>
                    <td>${dt.nhaCungCap.tenNhaCC}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/chuc-nang-quan-ly" method="post"
                              style="display: inline;">
                            <input type="hidden" name="maDtXoa" value="${dt.maDT}">
                            <button type="submit"
                                    onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ${dt.tenDT} không?')"
                                    style="background-color: #d9534f; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer;">
                                Xóa
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Không có sản phẩm nào để quản lý.</p>
    </c:otherwise>
</c:choose>

<%@ include file="Footer.jsp" %>
