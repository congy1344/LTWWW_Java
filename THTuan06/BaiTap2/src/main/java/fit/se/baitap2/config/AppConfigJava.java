package fit.se.baitap2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import fit.se.baitap2.models.Address;
import fit.se.baitap2.models.Employee;

@Configuration
public class AppConfigJava {

    @Bean
    public Address address() {
        return new Address("Da Nang", "DN", "Vietnam");
    }

    @Bean
    public Employee employee() {
        // Tiêm phụ thuộc bằng cách gọi phương thức @Bean khác
        return new Employee(301, "Pham Thi D", address());
    }
}