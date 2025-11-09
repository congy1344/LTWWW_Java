package fit.se.backend_danhmuc.repository;

import fit.se.backend_danhmuc.entity.TinTuc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TinTucRepository extends JpaRepository<TinTuc, Integer> {
    // Tương đương getTinTucByDanhMuc
    List<TinTuc> findByDanhMuc_MaDM(int maDM);
}