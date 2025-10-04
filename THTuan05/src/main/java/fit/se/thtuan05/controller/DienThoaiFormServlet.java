package fit.se.thtuan05.controller;

import fit.se.thtuan05.daos.DienThoaiDao;
import fit.se.thtuan05.daos.NhaCungCapDao;
import fit.se.thtuan05.daos.impl.DienThoaiImpl;
import fit.se.thtuan05.daos.impl.NhaCungCapImpl;
import fit.se.thtuan05.models.DienThoai;
import fit.se.thtuan05.models.NhaCungCap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet("/them-moi-san-pham")
public class DienThoaiFormServlet extends HttpServlet {

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
        request.setAttribute("dsNhaCungCap", nccDAO.layTatCa());
        request.getRequestDispatcher("/DienThoaiForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tenDt = request.getParameter("tenDt");
        String namSx = request.getParameter("namSanXuat");
        String cauHinh = request.getParameter("cauHinh");
        String maNccStr = request.getParameter("maNcc");

        Part filePart = request.getPart("hinhAnh");
        String fileName = extractFileName(filePart);

        try {
            long maNcc = Long.parseLong(maNccStr);
            NhaCungCap ncc = nccDAO.timTheoMa(maNcc);

            if (ncc != null) {
                String appPath = request.getServletContext().getRealPath("");
                String uploadPath = appPath + File.separator + "images" + File.separator + "products";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                DienThoai newDt = new DienThoai();
                newDt.setTenDT(tenDt);
                newDt.setNamSanXuat(Integer.parseInt(namSx));
                newDt.setCauHinh(cauHinh);
                newDt.setHinhAnh(fileName);
                newDt.setNhaCungCap(ncc);

                if (dtDAO.them(newDt)) {
                    response.sendRedirect("danh-sach-san-pham");
                    return;
                }
            }

            request.setAttribute("error", "Lỗi khi thêm sản phẩm. Vui lòng kiểm tra lại thông tin.");
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý dữ liệu: " + e.getMessage());
            doGet(request, response);
        }
    }

    private String extractFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.jpg";
    }
}