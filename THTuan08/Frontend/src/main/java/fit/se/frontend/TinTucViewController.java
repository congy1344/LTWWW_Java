package fit.se.frontend; // (hoặc package của bạn)

import fit.se.frontend.model.DanhMuc;
import fit.se.frontend.model.TinTuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference; // Thêm import này
import org.springframework.http.HttpMethod; // Thêm import này
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TinTucViewController {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL = "http://localhost:8080/api/tin-tuc";

    // 1. Lấy danh sách danh mục (ĐÃ SỬA)
    // Dùng "categories" thay vì "applicationScope.categories" cho sạch sẽ
    @ModelAttribute("categories")
    public List<DanhMuc> getCategories() {
        // Sửa: Dùng exchange để lấy đúng kiểu List<DanhMuc>
        List<DanhMuc> categories = restTemplate.exchange(
                API_URL + "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DanhMuc>>() {} // Đây là phần sửa
        ).getBody();
        return categories;
    }

    // 2. Hiển thị danh sách tin tức (ĐÃ SỬA)
    @GetMapping("/tin-tuc")
    public String showTinTucList(
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            Model model) {

        String url = API_URL;
        int selectedId = 0;

        if (categoryId != null && categoryId > 0) {
            url = API_URL + "?categoryId=" + categoryId;
            selectedId = categoryId;
        }

        // Sửa: Dùng exchange để lấy đúng kiểu List<TinTuc>
        List<TinTuc> listTinTuc = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TinTuc>>() {} // Đây là phần sửa
        ).getBody();

        model.addAttribute("listTinTuc", listTinTuc);
        model.addAttribute("selectedCategory", selectedId);

        return "DanhSachTinTuc"; // Trả về file /WEB-INF/views/DanhSachTinTuc.jsp
    }

    // 3. Hiển thị form thêm (Giữ nguyên)
    @GetMapping("/them-tin-tuc")
    public String showTinTucForm(Model model) {
        model.addAttribute("tinTuc", new TinTuc());
        return "TinTucForm"; // Trả về file /WEB-INF/views/TinTucForm.jsp
    }

    // 4. Xử lý thêm tin tức (Giữ nguyên)
    @PostMapping("/them-tin-tuc")
    public String addTinTuc(@ModelAttribute TinTuc tinTuc, RedirectAttributes redirectAttributes) {
        try {
            // postForObject hoạt động tốt, không cần sửa
            restTemplate.postForObject(API_URL, tinTuc, TinTuc.class);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Thêm tin tức thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("message", "Lỗi: " + e.getMessage());
        }
        return "redirect:/ket-qua";
    }

    // 5. Hiển thị trang quản lý (ĐÃ SỬA)
    @GetMapping("/quan-ly-tin-tuc")
    public String showManageForm(Model model) {
        // Sửa: Dùng exchange để lấy đúng kiểu List<TinTuc>
        List<TinTuc> listTinTuc = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TinTuc>>() {} // Đây là phần sửa
        ).getBody();

        model.addAttribute("listTinTuc", listTinTuc);
        return "QuanLyForm"; // Trả về /WEB-INF/views/QuanLyForm.jsp
    }

    // 6. Xử lý xóa tin tức (Giữ nguyên)
    @GetMapping("/quan-ly-tin-tuc-delete")
    public String deleteTinTuc(@RequestParam("maTT") int maTT, RedirectAttributes redirectAttributes) {
        try {
            restTemplate.delete(API_URL + "/" + maTT);
            redirectAttributes.addFlashAttribute("messageType", "success");
            redirectAttributes.addFlashAttribute("message", "Xóa tin tức thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("message", "Lỗi khi xóa: " + e.getMessage());
        }
        return "redirect:/ket-qua";
    }

    // 7. Controller cho trang kết quả (Giữ nguyên)
    @GetMapping("/ket-qua")
    public String showResultPage() {
        return "KetQua"; // Trả về /WEB-INF/views/KetQua.jsp
    }
}