package fit.se.thlab4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TinTuc {
    private Integer maTT;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 200, message = "Tiêu đề không được vượt quá 200 ký tự")
    private String tieuDe;

    @NotBlank(message = "Nội dung không được để trống")
    @Size(max = 255, message = "Nội dung không được vượt quá 255 ký tự")
    private String noiDungTT;

    @NotBlank(message = "Liên kết không được để trống")
    @Pattern(regexp = "^http://.*", message = "Liên kết phải bắt đầu bằng 'http://'")
    @Size(max = 255, message = "Liên kết không được vượt quá 255 ký tự")
    private String lienKet;

    @NotNull(message = "Danh mục không được để trống")
    private Integer maDM;

    private String tenDanhMuc;

    // Constructors
    public TinTuc() {
    }

    public TinTuc(Integer maTT, String tieuDe, String noiDungTT, String lienKet, Integer maDM, String tenDanhMuc) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.noiDungTT = noiDungTT;
        this.lienKet = lienKet;
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
    }

    // Getters and Setters
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
}
