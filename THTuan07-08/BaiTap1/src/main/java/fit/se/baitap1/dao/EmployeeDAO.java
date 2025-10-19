package fit.se.baitap1.dao;

import fit.se.baitap1.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);
    Employee getById(int id);
    List<Employee> getAll();
    void update(Employee employee);
    void deleteById(int id);
}