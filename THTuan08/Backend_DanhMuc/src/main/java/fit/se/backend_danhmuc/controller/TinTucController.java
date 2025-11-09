package fit.se.backend_danhmuc.controller;

import fit.se.backend_danhmuc.entity.DanhMuc;
import fit.se.backend_danhmuc.entity.TinTuc;
import fit.se.backend_danhmuc.services.TinTucService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tin-tuc")
@CrossOrigin // Cho phép frontend gọi
public class TinTucController {

    @Autowired
    private TinTucService tinTucService;

    @GetMapping
    public List<TinTuc> getTinTuc(@RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            return tinTucService.getTinTucByCategory(categoryId);
        }
        return tinTucService.getAllTinTuc();
    }
    
    @GetMapping("/categories")
    public List<DanhMuc> getCategories() {
        return tinTucService.getCategories();
    }

    // Thay thế doPost trong TinTucFormServlet
    @PostMapping
    public ResponseEntity<TinTuc> createTinTuc(@Valid @RequestBody TinTuc tinTuc) {
        TinTuc savedTinTuc = tinTucService.addTinTuc(tinTuc);
        return ResponseEntity.ok(savedTinTuc);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTinTuc(@PathVariable int id) {
        tinTucService.deleteTinTuc(id);
        return ResponseEntity.noContent().build();
    }
}