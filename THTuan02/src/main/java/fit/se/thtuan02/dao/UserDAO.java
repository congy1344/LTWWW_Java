package fit.se.thtuan02.dao;


import fit.se.thtuan02.connectdb.DBConnection;
import fit.se.thtuan02.model.UserForEx5;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Thêm mới user
    public void insert(UserForEx5 user) throws SQLException {
        String sql = "INSERT INTO users(first_name, last_name, dob) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            if (user.getDob() != null) {
                ps.setDate(3, Date.valueOf(user.getDob()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.executeUpdate();
        }
    }

    // Lấy tất cả users
    public List<UserForEx5> getAll() throws SQLException {
        List<UserForEx5> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UserForEx5 user = new UserForEx5(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null
                );
                users.add(user);
            }
        }
        return users;
    }

    // Lấy 1 user theo id
    public UserForEx5 getById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserForEx5(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null
                    );
                }
            }
        }
        return null;
    }

    // Cập nhật user
    public void update(UserForEx5 user) throws SQLException {
        String sql = "UPDATE users SET first_name=?, last_name=?, dob=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            if (user.getDob() != null) {
                ps.setDate(3, Date.valueOf(user.getDob()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        }
    }

    // Xoá user
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
