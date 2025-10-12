package fit.se.baitap2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import fit.se.baitap2.config.AppConfigJava;
import fit.se.baitap2.models.Employee;

public class MainJava {
    public static void main(String[] args) {
        // Khởi tạo Spring container từ lớp cấu hình
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigJava.class);

        Employee emp = context.getBean("employee", Employee.class);
        System.out.println(emp);

        ((AnnotationConfigApplicationContext) context).close();
    }
}