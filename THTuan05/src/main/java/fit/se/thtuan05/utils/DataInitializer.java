package fit.se.thtuan05.utils;

import fit.se.thtuan05.models.DienThoai;
import fit.se.thtuan05.models.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DataInitializer {

    public static void initData() {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            NhaCungCap samsung = new NhaCungCap();
            samsung.setTenNhaCC("Samsung Việt Nam");
            samsung.setDiaChi("TP. Hồ Chí Minh");
            samsung.setSoDienThoai("0901234567");
            em.persist(samsung);

            NhaCungCap apple = new NhaCungCap();
            apple.setTenNhaCC("Apple Singapore");
            apple.setDiaChi("Singapore");
            apple.setSoDienThoai("0917654321");
            em.persist(apple);


            DienThoai s24 = new DienThoai();
            s24.setTenDT("Galaxy S24 Ultra");
            s24.setNamSanXuat(2024);
            s24.setCauHinh("Snapdragon 8 Gen 3, RAM 12GB");
            s24.setHinhAnh("s24u.jpg");
            s24.setNhaCungCap(samsung);
            em.persist(s24);

            DienThoai a55 = new DienThoai();
            a55.setTenDT("Galaxy A55");
            a55.setNamSanXuat(2024);
            a55.setCauHinh("Exynos 1480, RAM 8GB");
            a55.setHinhAnh("a55.jpg");
            a55.setNhaCungCap(samsung);
            em.persist(a55);

            DienThoai ip15 = new DienThoai();
            ip15.setTenDT("iPhone 15 Pro Max");
            ip15.setNamSanXuat(2023);
            ip15.setCauHinh("A17 Bionic, RAM 8GB");
            ip15.setHinhAnh("ip15pm.png");
            ip15.setNhaCungCap(apple);
            em.persist(ip15);

            DienThoai ipse = new DienThoai();
            ipse.setTenDT("iPhone SE (2022)");
            ipse.setNamSanXuat(2022);
            ipse.setCauHinh("A15 Bionic, RAM 4GB");
            ipse.setHinhAnh("ipse3.jpg");
            ipse.setNhaCungCap(apple);
            em.persist(ipse);

            tx.commit();
            System.out.println("Khởi tạo dữ liệu thành công: 2 NCC và 4 ĐT.");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Lỗi khi khởi tạo dữ liệu: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        initData();
    }
}