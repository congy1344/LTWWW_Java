package fit.se.thtuan05.controller;

import fit.se.thtuan05.daos.DienThoaiDao;
import fit.se.thtuan05.daos.NhaCungCapDao;
import fit.se.thtuan05.daos.impl.DienThoaiImpl;
import fit.se.thtuan05.daos.impl.NhaCungCapImpl;
import fit.se.thtuan05.models.DienThoai;
import fit.se.thtuan05.models.NhaCungCap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/danh-sach-san-pham")
public class DanhSachDienThoaiNCCServlet extends HttpServlet {

    private NhaCungCapDao nccDAO;
    private DienThoaiDao dtDAO;

    @Override
    public void init() throws ServletException {
        this.nccDAO = new NhaCungCapImpl();
        this.dtDAO = new DienThoaiImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maNccParam = request.getParameter("maNcc");
        String timKiemNcc = request.getParameter("timKiemNcc");

        List<NhaCungCap> dsNhaCungCap;
        List<DienThoai> dsDienThoai = null;

        if (timKiemNcc != null && !timKiemNcc.trim().isEmpty()) {
            dsNhaCungCap = nccDAO.timTheoThongTin(timKiemNcc);
        } else {
            dsNhaCungCap = nccDAO.layTatCa();
        }

        if (maNccParam != null && !maNccParam.trim().isEmpty()) {
            try {
                long maNcc = Long.parseLong(maNccParam);
                dsDienThoai = dtDAO.layTheoNhaCungCap(maNcc);
                request.setAttribute("nccHienTai", nccDAO.timTheoMa(maNcc));
            } catch (NumberFormatException e) {
            }
        }

        request.setAttribute("dsNhaCungCap", dsNhaCungCap);
        request.setAttribute("dsDienThoai", dsDienThoai);

        request.getRequestDispatcher("/DanhSachDienThoaiNCC.jsp").forward(request, response);
    }
}