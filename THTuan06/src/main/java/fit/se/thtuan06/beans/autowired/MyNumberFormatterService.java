package fit.se.thtuan06.beans.autowired;

import org.springframework.stereotype.Component;

@Component
public class MyNumberFormatterService {
    private final MyNumberFormatter myNumberFormatter;

    // @Autowired là không bắt buộc ở đây vì chỉ có 1 constructor
    public MyNumberFormatterService(MyNumberFormatter myNumberFormatter) {
        this.myNumberFormatter = myNumberFormatter;
    }

    public void printFormat(double number) {
        System.out.println(myNumberFormatter.format(number));
    }
}