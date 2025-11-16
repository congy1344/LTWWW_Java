package iuh.fit.se.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @GetMapping
    @PreAuthorize("hasAuthority('DOCUMENT:READ') or hasRole('ADMIN')")
    public String list() {
        return "Danh sách tài liệu (read)";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('DOCUMENT:WRITE') or hasRole('ADMIN')")
    public String create() {
        return "Tạo tài liệu thành công";
    }
}