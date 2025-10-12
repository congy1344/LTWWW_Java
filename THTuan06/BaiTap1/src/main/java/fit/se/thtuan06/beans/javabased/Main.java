package fit.se.thtuan06.beans.javabased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fit.se.thtuan06.beans.javabased.AppConfig;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo Spring Container từ lớp cấu hình Java
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Lấy bean 'user' từ container
        // Spring sẽ tự động tạo và inject bean 'group' vào bean 'user'
        User user = context.getBean("user", User.class);

        // 3. Lấy bean 'group' riêng lẻ
        Group group = context.getBean("group", Group.class);

        // 4. In kết quả
        System.out.println("--- Lấy User Bean ---");
        System.out.println(user);

        System.out.println("\n--- Lấy Group Bean ---");
        System.out.println(group);

        // 5. Đóng container (chỉ cần thiết với các lớp context cụ thể như AnnotationConfigApplicationContext)
        ((AnnotationConfigApplicationContext) context).close();
    }
}