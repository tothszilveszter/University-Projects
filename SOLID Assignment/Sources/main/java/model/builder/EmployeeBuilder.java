package model.builder;

import model.Role;
import model.Employee;

import java.util.List;

public class EmployeeBuilder {

    private Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
    }

    public EmployeeBuilder setId(Long id){
        employee.setId(id);
        return this;
    }
    public EmployeeBuilder setUsername(String username) {
        employee.setUsername(username);
        return this;
    }

    public EmployeeBuilder setPassword(String password) {
        employee.setPassword(password);
        return this;
    }

    public EmployeeBuilder setRoles(List<Role> roles) {
        employee.setRoles(roles);
        return this;
    }

    public Employee build() {
        return employee;
    }


}