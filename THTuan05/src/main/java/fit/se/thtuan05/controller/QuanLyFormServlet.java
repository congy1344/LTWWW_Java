package fit.se.thtuan05.controller;


import fit.se.thtuan05.daos.DienThoaiDao;
import fit.se.thtuan05.daos.impl.DienThoaiImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chuc-nang-quan-ly")
public class QuanLyFormServlet extends HttpServlet {

    private DienThoaiDao dtDAO;

    @Override
    public void init() throws ServletException {
        this.dtDAO = new DienThoaiImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("dsDienThoai", dtDAO.timKiem(""));
        request.getRequestDispatcher("/QuanLyForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maDtCanXoa = request.getParameter("maDtXoa");

        if (maDtCanXoa != null && !maDtCanXoa.trim().isEmpty()) {
            if (dtDAO.xoa(maDtCanXoa)) {
                request.setAttribute("successMessage", "Xóa sản phẩm thành công.");
            } else {
                request.setAttribute("errorMessage", "Không thể xóa sản phẩm. Vui lòng thử lại.");
            }
        }
        doGet(request, response);
    }
}
