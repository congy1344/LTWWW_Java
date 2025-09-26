package fit.se.thlab4.dao;

import fit.se.thlab4.model.Product;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final DataSource dataSource;

    public ProductDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, model, price, imgURL FROM product";
        List<Product> list = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                String imgURL = rs.getString("imgURL");
                list.add(new Product(id, model, "", 0, price, imgURL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}