
package fit.se.thlab4.listener;

import fit.se.thlab4.dao.DanhSachTinTucQuanLy;
import fit.se.thlab4.model.DanhMuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.sql.DataSource;
import java.util.List;

@WebListener
public class StartupListener implements ServletContextListener {
    
    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy(dataSource);
        List<DanhMuc> categories = dao.findAllDanhMuc();
        ServletContext context = sce.getServletContext();
        context.setAttribute("categories", categories);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}
