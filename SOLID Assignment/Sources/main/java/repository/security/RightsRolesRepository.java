package repository.security;

import model.Right;
import model.Role;
import model.Employee;

import java.util.List;


public interface RightsRolesRepository {

    void addRole(String role);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    void addRolesToUser(Employee employee, List<Role> roles);

    List<Role> findRolesForUser(Long userId);

}