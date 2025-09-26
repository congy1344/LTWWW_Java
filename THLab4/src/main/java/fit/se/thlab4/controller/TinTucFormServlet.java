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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "TinTucFormServlet", urlPatterns = {"/them-tin-tuc"})
public class TinTucFormServlet extends HttpServlet {

    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy tinTucDAO;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.tinTucDAO = new DanhSachTinTucQuanLy(this.dataSource);
        if (this.dataSource == null) {
            throw new ServletException("DataSource jdbc/QUANLYDANHMUC không được khởi tạo!");
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        req.setAttribute("tinTuc", new TinTuc());
        req.getRequestDispatcher("/views/TinTucForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String tieuDe = req.getParameter("tieuDe");
        String noiDungTT = req.getParameter("noiDungTT");
        String lienKet = req.getParameter("lienKet");
        String maDMStr = req.getParameter("maDM");

        Integer maDM = null;
        try {
            if (maDMStr != null && !maDMStr.trim().isEmpty()) {
                maDM = Integer.parseInt(maDMStr);
            }
        } catch (NumberFormatException e) {
            // Sẽ được xử lý bởi validator
        }

        TinTuc tinTuc = new TinTuc(null, tieuDe, noiDungTT, lienKet, maDM, null);
        Set<ConstraintViolation<TinTuc>> violations = validator.validate(tinTuc);

        if (!violations.isEmpty()) {
            req.setAttribute("tinTuc", tinTuc);
            req.setAttribute("errors", violations);
            req.getRequestDispatcher("/views/TinTucForm.jsp").forward(req, resp);
        } else {
            try {
                boolean success = tinTucDAO.addTinTuc(tinTuc);
                String message = success ? "Thêm tin tức thành công!" : "Có lỗi xảy ra khi thêm tin tức!";
                String messageType = success ? "success" : "error";
                req.setAttribute("message", message);
                req.setAttribute("messageType", messageType);
                req.getRequestDispatcher("/views/KetQua.jsp").forward(req, resp);
            } catch (RuntimeException e) {
                req.setAttribute("tinTuc", tinTuc);
                req.setAttribute("message", "Lỗi: " + e.getMessage());
                req.setAttribute("messageType", "error");
                req.getRequestDispatcher("/views/TinTucForm.jsp").forward(req, resp);
            }
        }
    }
}
