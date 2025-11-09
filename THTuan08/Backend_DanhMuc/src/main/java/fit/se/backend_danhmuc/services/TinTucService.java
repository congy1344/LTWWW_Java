package fit.se.backend_danhmuc.services;


import fit.se.backend_danhmuc.entity.DanhMuc;
import fit.se.backend_danhmuc.entity.TinTuc;
import fit.se.backend_danhmuc.repository.DanhMucRepository;
import fit.se.backend_danhmuc.repository.TinTucRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TinTucService {

    @Autowired
    private TinTucRepository tinTucRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;
    
    // Thay thế cho việc StartupListener lưu categories vào ServletContext
    // Chúng ta sẽ cache danh sách này
    private final List<DanhMuc> categoriesCache = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void loadCategories() {
        // Đây là cách thay thế StartupListener
        categoriesCache.addAll(danhMucRepository.findAll());
    }

    public List<DanhMuc> getCategories() {
        return categoriesCache;
    }

    public List<TinTuc> getAllTinTuc() {
        return tinTucRepository.findAll();
    }
    
    public List<TinTuc> getTinTucByCategory(int categoryId) {
        if (categoryId == 0) {
            return tinTucRepository.findAll();
        }
        return tinTucRepository.findByDanhMuc_MaDM(categoryId);
    }
    
    public TinTuc addTinTuc(TinTuc tinTuc) {
        // Logic validation và lưu
        return tinTucRepository.save(tinTuc);
    }

    public void deleteTinTuc(int maTT) {
        tinTucRepository.deleteById(maTT);
    }
}