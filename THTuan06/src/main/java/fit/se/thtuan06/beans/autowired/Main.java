package fit.se.thtuan06.beans.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyNumberFormatterService service = context.getBean(MyNumberFormatterService.class);
        service.printFormat(1000d);
    }
}