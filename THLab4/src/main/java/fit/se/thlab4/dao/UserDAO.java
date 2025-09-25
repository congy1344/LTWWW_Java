package fit.se.thlab4.dao;

import fit.se.thlab4.model.User;
import fit.se.thlab4.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * Lưu user vào database
     */
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

    /**
     * Lấy tất cả user từ database (không lấy password)
     */
    public static List<User> findAll() {
        String sql = "SELECT first_name, last_name, email, birthday, gender FROM users ORDER BY first_name, last_name";
        List<User> userList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Executing query: " + sql);

            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));

                // Xử lý birthday an toàn
                java.sql.Date sqlDate = rs.getDate("birthday");
                if (sqlDate != null) {
                    user.setBirthday(sqlDate.toLocalDate());
                }

                user.setGender(rs.getString("gender"));
                // Không set password để bảo mật

                userList.add(user);
                System.out.println("Added user: " + user.getFirstName() + " " + user.getLastName());
            }

            System.out.println("Total users found: " + userList.size());

        } catch (Exception e) {
            System.err.println("Error in findAll(): " + e.getMessage());
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * Kiểm tra email đã tồn tại chưa
     */
    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}