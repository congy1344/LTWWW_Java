package fit.se.backend_danhmuc.repository;

import fit.se.backend_danhmuc.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {
    // Đã có sẵn findAll, findById, save, delete
}