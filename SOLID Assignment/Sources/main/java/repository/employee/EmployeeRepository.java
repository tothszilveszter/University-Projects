package repository.employee;

import model.Employee;
import model.validation.Notification;

import java.util.List;


public interface EmployeeRepository {

    List<Employee> findAll();

    Notification<Employee> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    boolean save(Employee employee);

    boolean update(String id, Employee employee);

    boolean delete(String id);

    void removeAll();

}