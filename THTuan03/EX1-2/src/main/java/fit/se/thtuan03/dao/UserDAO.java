package fit.se.thtuan03.dao;

import fit.se.thtuan03.model.User;
import fit.se.thtuan03.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {
    public static boolean save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, birthday, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setDate(5, java.sql.Date.valueOf(user.getBirthday())); // LocalDate -> SQL Date
            stmt.setString(6, user.getGender());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
