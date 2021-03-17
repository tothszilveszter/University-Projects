package service.employee;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;


public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<Boolean> update(String id, String username, String password);

    boolean delete(String id);

    Notification<Employee> login(String username, String password) throws AuthenticationException;

    void view();

    Employee findById(Long id);


}