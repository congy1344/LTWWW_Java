package fit.se.thtuan05.daos.impl;

import fit.se.thtuan05.daos.NhaCungCapDao;
import fit.se.thtuan05.models.NhaCungCap;
import fit.se.thtuan05.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

public class NhaCungCapImpl implements NhaCungCapDao {
    @Override
    public NhaCungCap timTheoMa(long maNcc) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        NhaCungCap ncc = em.find(NhaCungCap.class, maNcc);
        return ncc;
    }

    @Override
    public List<NhaCungCap> timTheoThongTin(String thongTin) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        try {
            String jpql = "select n from NhaCungCap n where n.maNCC = :tt or n.tenNhaCC = :ttParam or n.diaChi = :ttParam or n.soDienThoai = :ttParam";
            TypedQuery<NhaCungCap> query = em.createQuery(jpql, NhaCungCap.class);
            query.setParameter("tt", Long.parseLong(thongTin));
            query.setParameter("ttParam", "%" + thongTin + "%");

            return query.getResultList();
        } catch (NumberFormatException e) {
            String jpql = "SELECT n FROM NhaCungCap n WHERE n.tenNhaCC LIKE :ttParam OR n.diaChi LIKE :ttParam OR n.soDienThoai LIKE :ttParam";
            TypedQuery<NhaCungCap> query = em.createQuery(jpql, NhaCungCap.class);
            query.setParameter("ttParam", "%" + thongTin + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<NhaCungCap> layTatCa() {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        List<NhaCungCap> list = em.createQuery("SELECT n FROM NhaCungCap n", NhaCungCap.class).getResultList();
        em.close();
        return list;
    }

    @Override
    public boolean them(NhaCungCap ncc) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ncc);
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
    public boolean capNhat(NhaCungCap ncc) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(ncc);
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
    public boolean xoa(long maNcc) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            NhaCungCap ncc = em.find(NhaCungCap.class, maNcc);
            if (ncc != null) {
                em.remove(ncc);
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
}
