package fit.se.thlab4.dao;

import fit.se.thlab4.model.Product;
import java.util.List;

public interface ProductDAO {
    public List<Product> findAll();
}
