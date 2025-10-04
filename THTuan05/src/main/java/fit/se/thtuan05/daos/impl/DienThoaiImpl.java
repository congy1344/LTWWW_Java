package fit.se.thtuan05.daos.impl;
import fit.se.thtuan05.daos.DienThoaiDao;
import fit.se.thtuan05.models.DienThoai;
import fit.se.thtuan05.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class DienThoaiImpl implements DienThoaiDao {
    @Override
    public DienThoai timTheoMa(String maDt) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        DienThoai dt = em.find(DienThoai.class, maDt);
        em.close();
        return dt;
    }

    @Override
    public List<DienThoai> layTheoNhaCungCap(long maNcc) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        List<DienThoai> list = em.createQuery(
                        "SELECT dt FROM DienThoai dt WHERE dt.nhaCungCap.maNCC = :maNcc", DienThoai.class)
                .setParameter("maNcc", maNcc)
                .getResultList();
        em.close();
        return list;
    }

    @Override
    public List<DienThoai> timKiem(String tuKhoa) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        String jpql = "SELECT dt FROM DienThoai dt WHERE dt.tenDT LIKE :keyword OR dt.cauHinh LIKE :keyword";
        List<DienThoai> list = em.createQuery(jpql, DienThoai.class)
                .setParameter("keyword", "%" + tuKhoa + "%")
                .getResultList();
        em.close();
        return list;
    }

    @Override
    public boolean them(DienThoai dt) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(dt);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean xoa(String maDt) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DienThoai dt = em.find(DienThoai.class, maDt);
            if (dt != null) {
                em.remove(dt);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean capNhat(DienThoai dt) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(dt);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}