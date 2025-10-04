package fit.se.thtuan05.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dienthoais")
public class DienThoai {
    //DIENTHOAI(MADT, TENDT, NAMSANXUAT, CAUHINH, MANCC, HINHANH)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dt")
    private long maDT;

    @Column(name = "ten_dt")
    private String tenDT;

    @Column(name = "nam_san_xuat")
    private int namSanXuat;

    @Column(name = "cau_hinh")
    private String cauHinh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_ncc")
    private NhaCungCap nhaCungCap;

    @Column(name = "hinh_anh")
    private String hinhAnh;

}
