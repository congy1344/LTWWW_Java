package fit.se.backend_danhmuc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "DANHMUC")
public class DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDM;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 255)
    private String tenDanhMuc;

    @OneToMany(mappedBy = "danhMuc")
    private List<TinTuc> tinTucs;

    public Integer getMaDM() {
        return maDM;
    }

    public void setMaDM(Integer maDM) {
        this.maDM = maDM;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public List<TinTuc> getTinTucs() {
        return tinTucs;
    }

    public void setTinTucs(List<TinTuc> tinTucs) {
        this.tinTucs = tinTucs;
    }
}