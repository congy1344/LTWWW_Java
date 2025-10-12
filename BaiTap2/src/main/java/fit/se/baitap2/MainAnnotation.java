package fit.se.baitap2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import fit.se.baitap2.config.AppConfigAnnotation;
import fit.se.baitap2.models.Employee;

public class MainAnnotation {
    public static void main(String[] args) {
        // Khởi tạo Spring container từ lớp cấu hình
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigAnnotation.class);

        Employee emp = context.getBean(Employee.class);
        System.out.println(emp);

        ((AnnotationConfigApplicationContext) context).close();
    }
}