package fit.se.thlab4.dao;

import fit.se.thlab4.model.TinTuc;

import java.util.List;

public interface TinTucDAO {
    List<TinTuc> findAllTinTuc();
    List<TinTuc> findByDanhMuc(int maDM);
    boolean insertTinTuc(TinTuc tinTuc);
    boolean deleteTinTuc(int maTT);
    TinTuc findTinTucById(int maTT);
    boolean updateTinTuc(TinTuc tinTuc);
}