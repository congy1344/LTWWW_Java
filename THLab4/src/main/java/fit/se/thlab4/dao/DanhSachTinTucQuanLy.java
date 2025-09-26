package fit.se.thlab4.dao;

import fit.se.thlab4.model.DanhMuc;
import fit.se.thlab4.model.TinTuc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhSachTinTucQuanLy {

    private DataSource dataSource;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TinTuc> getTinTucByDanhMuc(int categoryId) {
        List<TinTuc> result = new ArrayList<>();
        String sql = "SELECT t.MATT, t.TIEUDE, t.NOIDUNGTT, t.LIENKET, t.MADM, d.TENDANHMUC " +
                "FROM TINTUC t LEFT JOIN DANHMUC d ON t.MADM = d.MADM WHERE t.MADM = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TinTuc tinTuc = new TinTuc(
                            rs.getInt("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            rs.getInt("MADM"),
                            rs.getString("TENDANHMUC")
                    );
                    result.add(tinTuc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy tin tức theo danh mục: " + e.getMessage());
        }
        return result;
    }

    public boolean addTinTuc(TinTuc tinTuc) {
        String sql = "INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tinTuc.getTieuDe());
            ps.setString(2, tinTuc.getNoiDungTT());
            ps.setString(3, tinTuc.getLienKet());
            ps.setInt(4, tinTuc.getMaDM());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm tin tức: " + e.getMessage());
        }
    }

    public boolean deleteTinTuc(int maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa tin tức: " + e.getMessage());
        }
    }

    public List<TinTuc> findAllTinTuc() {
        List<TinTuc> result = new ArrayList<>();
        String sql = "SELECT t.MATT, t.TIEUDE, t.NOIDUNGTT, t.LIENKET, t.MADM, d.TENDANHMUC " +
                "FROM TINTUC t LEFT JOIN DANHMUC d ON t.MADM = d.MADM";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TinTuc tinTuc = new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC")
                );
                result.add(tinTuc);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tin tức: " + e.getMessage());
        }
        return result;
    }

    public List<DanhMuc> findAllDanhMuc() {
        List<DanhMuc> result = new ArrayList<>();
        String sql = "SELECT MADM, TENDANHMUC FROM DANHMUC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC")
                );
                result.add(danhMuc);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách danh mục: " + e.getMessage());
        }
        return result;
    }
}
