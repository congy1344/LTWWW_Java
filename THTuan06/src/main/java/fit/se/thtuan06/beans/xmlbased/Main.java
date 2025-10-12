package fit.se.thtuan06.beans.xmlbased;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fit.se.thtuan06.beans.xmlbased.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static ApplicationContext context;

    public static void main(String[] args) {
        //XML Based
        context = new ClassPathXmlApplicationContext("beans.xml");
//        Student student1 = context.getBean("student1", Student.class);
//        System.out.println(student1);

        Student student2 = context.getBean("student2", Student.class);
        System.out.println(student2);


    }
}
