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

@WebServlet(name = "QuanLyFormServlet", urlPatterns = {"/quan-ly-tin-tuc"})
public class QuanLyFormServlet extends HttpServlet {

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
        if ("delete".equals(action)) {
            deleteTinTuc(req, resp);
        } else {
            showManageTinTuc(req, resp);
        }
    }

    private void showManageTinTuc(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<TinTuc> listTinTuc = tinTucDAO.findAllTinTuc();
        req.setAttribute("listTinTuc", listTinTuc);
        req.getRequestDispatcher("/views/QuanLyForm.jsp").forward(req, resp);
    }

    private void deleteTinTuc(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String maTTStr = req.getParameter("maTT");
        try {
            int maTT = Integer.parseInt(maTTStr);
            boolean success = tinTucDAO.deleteTinTuc(maTT);
            String message = success ? "Xóa tin tức thành công!" : "Không thể xóa tin tức!";
            String messageType = success ? "success" : "error";
            req.setAttribute("message", message);
            req.setAttribute("messageType", messageType);
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Mã tin tức không hợp lệ!");
            req.setAttribute("messageType", "error");
        } catch (RuntimeException e) {
            req.setAttribute("message", "Lỗi: " + e.getMessage());
            req.setAttribute("messageType", "error");
        }
        req.getRequestDispatcher("/views/KetQua.jsp").forward(req, resp);
    }
}
