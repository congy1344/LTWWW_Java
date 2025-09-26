package fit.se.thlab4.controller;

import fit.se.thlab4.dao.DanhSachTinTucQuanLy;
import fit.se.thlab4.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DanhSachTinTucServlet", urlPatterns = {"/tin-tuc"})
public class DanhSachTinTucServlet extends HttpServlet {

    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy tinTucDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.tinTucDAO = new DanhSachTinTucQuanLy(this.dataSource);
        if (this.dataSource == null) {
            throw new ServletException("DataSource jdbc/QUANLYDANHMUC không được khởi tạo!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        if ("category".equals(action)) {
            showTinTucByCategory(req, resp);
        } else {
            showAllTinTuc(req, resp);
        }
    }

    private void showAllTinTuc(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<TinTuc> listTinTuc = tinTucDAO.findAllTinTuc();
        req.setAttribute("listTinTuc", listTinTuc);
        req.setAttribute("selectedCategory", 0);
        req.getRequestDispatcher("/views/DanhSachTinTuc.jsp").forward(req, resp);
    }

    private void showTinTucByCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String categoryIdStr = req.getParameter("categoryId");
        int categoryId = 0;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            // Mặc định hiển thị tất cả nếu categoryId không hợp lệ
        }

        List<TinTuc> listTinTuc = categoryId == 0 ? tinTucDAO.findAllTinTuc() : tinTucDAO.getTinTucByDanhMuc(categoryId);
        req.setAttribute("listTinTuc", listTinTuc);
        req.setAttribute("selectedCategory", categoryId);
        req.getRequestDispatcher("/views/DanhSachTinTuc.jsp").forward(req, resp);
    }
}
