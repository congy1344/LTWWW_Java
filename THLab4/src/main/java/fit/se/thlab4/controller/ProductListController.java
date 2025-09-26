package fit.se.thlab4.controller;

import fit.se.thlab4.dao.ProductDAO;
import fit.se.thlab4.dao.ProductDAOImpl;
import fit.se.thlab4.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/productdb")
    private DataSource dataSource;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAOImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> listProduct = productDAO.findAll();
            request.setAttribute("ds", listProduct);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModelList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error loading product list: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModelList.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}