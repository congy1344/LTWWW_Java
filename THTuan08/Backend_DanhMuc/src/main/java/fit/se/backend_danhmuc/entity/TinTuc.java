package fit.se.backend_danhmuc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TINTUC")
public class TinTuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTT;

    @NotBlank
    @Size(max = 200)
    private String tieuDe;

    @NotBlank
    @Size(max = 255)
    private String noiDungTT;

    @NotBlank
    @Pattern(regexp = "^http://.*")
    @Size(max = 255)
    private String lienKet;

    @ManyToOne // Quan hệ Nhiều-1
    @JoinColumn(name = "maDM")
    private DanhMuc danhMuc;

    public Integer getMaTT() {
        return maTT;
    }

    public void setMaTT(Integer maTT) {
        this.maTT = maTT;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDungTT() {
        return noiDungTT;
    }

    public void setNoiDungTT(String noiDungTT) {
        this.noiDungTT = noiDungTT;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}