package fit.se.thtuan02.dao;

import fit.se.thtuan02.connectdb.DBConnection;
import fit.se.thtuan02.model.Contact;

import java.sql.*;

public class ContactDAO {
    public void insert(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts(first_name, last_name, photo) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            if (contact.getPhoto() != null) {
                ps.setBytes(3, contact.getPhoto());
            } else {
                ps.setNull(3, Types.BLOB);
            }
            ps.executeUpdate();
        }
    }
}
