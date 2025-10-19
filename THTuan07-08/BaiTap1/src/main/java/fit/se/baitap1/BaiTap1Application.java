package fit.se.baitap1;

import fit.se.baitap1.dao.EmployeeDAO;
import fit.se.baitap1.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"fit.se.baitap1.dao", "fit.se.baitap1.model", "fit.se.baitap1.dao.impl"})
public class BaiTap1Application {

    public static void main(String[] args) {
        SpringApplication.run(BaiTap1Application.class, args);
    }

    @Bean
    public CommandLineRunner run(EmployeeDAO employeeDAO) {
        return args -> {
            System.out.println("--- Thêm nhân viên mới ---");
            Employee newEmployee = new Employee("Nguyen Van A", "Developer");
            employeeDAO.save(newEmployee);
            System.out.println("Đã lưu nhân viên: " + newEmployee.getName());

            System.out.println("\n--- Danh sách tất cả nhân viên ---");
            List<Employee> employees = employeeDAO.getAll();
            employees.forEach(System.out::println);

            System.out.println("\n--- Lấy nhân viên có ID = 1 ---");
            Employee empById = employeeDAO.getById(1);
            System.out.println("Tìm thấy: " + empById);

            System.out.println("\n--- Cập nhật vai trò cho nhân viên ID = 1 ---");
            empById.setRole("Senior Developer");
            employeeDAO.update(empById);
            System.out.println("Đã cập nhật: " + employeeDAO.getById(1));

            System.out.println("\n--- Xóa nhân viên có ID = 1 ---");
            employeeDAO.deleteById(1);
            System.out.println("Đã xóa nhân viên có ID = 1.");

            System.out.println("\n--- Danh sách nhân viên sau khi xóa ---");
            employeeDAO.getAll().forEach(System.out::println);
        };
    }
}