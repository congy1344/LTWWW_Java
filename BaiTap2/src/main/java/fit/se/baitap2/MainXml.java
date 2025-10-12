package fit.se.baitap2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fit.se.baitap2.models.Employee;

public class MainXml {
    public static void main(String[] args) {
        // Khởi tạo Spring container từ file beans.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("--- Setter Injection Demo ---");
        Employee emp1 = context.getBean("employeeSetter", Employee.class);
        System.out.println(emp1);

        System.out.println("\n--- Constructor Injection Demo ---");
        Employee emp2 = context.getBean("employeeConstructor", Employee.class);
        System.out.println(emp2);

        ((ClassPathXmlApplicationContext) context).close();
    }
}