package service.employee;

import model.Role;
import model.Employee;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import model.validation.EmployeeValidator;
import repository.security.RightsRolesRepository;
import repository.employee.AuthenticationException;
import repository.employee.EmployeeRepository;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;


public class AuthenticationServiceMySQL implements AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public AuthenticationServiceMySQL(EmployeeRepository employeeRepository, RightsRolesRepository rightsRolesRepository) {
        this.employeeRepository = employeeRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<Boolean> register(String username, String password) {
        Role customerRole = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
        Employee employee = new EmployeeBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(customerRole))
                .build();

        EmployeeValidator employeeValidator = new EmployeeValidator(employee);
        boolean userValid = employeeValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            employeeValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            employee.setPassword(encodePassword(password));
            userRegisterNotification.setResult(employeeRepository.save(employee));
        }
        return userRegisterNotification;
    }

    @Override
    public Notification<Boolean> update(String id,String username, String password) {
        Employee employee = new EmployeeBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        EmployeeValidator employeeValidator = new EmployeeValidator(employee);
        boolean userValid = employeeValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            employeeValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            employee.setPassword(encodePassword(password));
            userRegisterNotification.setResult(employeeRepository.update(id, employee));
        }
        return userRegisterNotification;
    }

    @Override
    public boolean delete(String id) {
        return employeeRepository.delete(id);
    }

    @Override
    public Notification<Employee> login(String username, String password) throws AuthenticationException {
        return employeeRepository.findByUsernameAndPassword(username, encodePassword(password));
    }

    @Override
    public void view() {
        List<Employee> employees=employeeRepository.findAll();
        for (Employee x: employees){
            System.out.print("Id:"+x.getId()+" | username:"+x.getUsername()+" | role:");
            List<Role> roles=rightsRolesRepository.findRolesForUser(x.getId());
            for (Role r : roles)
            {
                System.out.print(r.getRole() + " ");
            }
            System.out.println(" ");
        }
    }

    @Override
    public Employee findById(Long idToFind) {
        List<Employee> employees=employeeRepository.findAll();
        for(Employee x : employees){
            if (x.getId().equals(idToFind))
                return x;
        }
        return null;
    }


    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}