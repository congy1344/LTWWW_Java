package fit.se.frontend.model;

// Dùng để hứng JSON từ backend
public class DanhMuc {
    private Integer maDM;
    private String tenDanhMuc;

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