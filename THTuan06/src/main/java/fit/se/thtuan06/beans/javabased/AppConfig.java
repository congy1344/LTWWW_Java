package fit.se.thtuan06.beans.javabased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Đánh dấu đây là lớp cấu hình nguồn của các định nghĩa bean [cite: 527]
public class AppConfig {

    @Bean // Định nghĩa một bean có tên là "group"
    public Group group() {
        return new Group("Admin Group");
    }

    @Bean // Định nghĩa một bean có tên là "user"
    public User user() {
        // Ví dụ về việc tiêm phụ thuộc (Dependency Injection)
        // Bằng cách gọi phương thức @Bean khác trong cùng một lớp [cite: 528]
        return new User("adminUser", "secretPassword", group());
    }
}