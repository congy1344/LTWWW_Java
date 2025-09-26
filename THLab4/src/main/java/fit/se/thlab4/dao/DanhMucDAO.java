package fit.se.thlab4.dao;

import fit.se.thlab4.model.DanhMuc;

import java.util.List;

public interface DanhMucDAO {
    List<DanhMuc> findAllDanhMuc();
    DanhMuc findDanhMucById(int maDM);
    boolean insertDanhMuc(DanhMuc danhMuc);
    boolean updateDanhMuc(DanhMuc danhMuc);
    boolean deleteDanhMuc(int maDM);
}