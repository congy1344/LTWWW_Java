package fit.se.thlab4.model;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static final List<Product> ds = new ArrayList<>();

    public static List<Product> queryProducts() {
        return ds;
    }
}