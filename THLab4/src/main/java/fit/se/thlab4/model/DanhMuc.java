package fit.se.thlab4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DanhMuc {
    private Integer maDM;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 255, message = "Tên danh mục không được vượt quá 255 ký tự")
    private String tenDanhMuc;

    // Constructors
    public DanhMuc() {
    }

    public DanhMuc(Integer maDM, String tenDanhMuc) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
    }

    // Getters and Setters
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
