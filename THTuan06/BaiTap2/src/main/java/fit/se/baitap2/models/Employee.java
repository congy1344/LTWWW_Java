package fit.se.baitap2.models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Đánh dấu lớp này là một Spring bean
public class Employee {
    private int id = 201; // Gán giá trị mặc định
    private String name = "Le Van C";
    private Address address;

    @Autowired
    public Employee(Address address) {
        this.address = address;
    }

    // Constructors
    public Employee() {
    }

    // Constructor để dùng cho Constructor Injection
    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name='" + name + "', address=" + address + "]";
    }
}