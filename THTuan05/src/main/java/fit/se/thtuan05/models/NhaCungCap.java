package fit.se.thtuan05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhacungcaps")
public class NhaCungCap {
//    NHACUNGCAP(MANCC, TENNHACC, DIACHI, SODIENTHOAI)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ncc")
    private long maNCC;

    @Column(name = "ten_nha_cc")
    private String tenNhaCC;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DienThoai> dsDienThoai;

}
