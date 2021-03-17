package repository.employee;

import model.Client;
import model.Employee;
import model.Role;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;


public class EmployeeRepositoryMySQL implements EmployeeRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public EmployeeRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                employees.add(getEmployeeFromResultSet(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
//
        return employees;
    }

    @Override
    public Notification<Employee> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        Notification<Employee> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                Employee employee = new EmployeeBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(employee);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean save(Employee employee) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, employee.getUsername());
            insertUserStatement.setString(2, employee.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            employee.setId(userId);

            rightsRolesRepository.addRolesToUser(employee, employee.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(String id, Employee employee) {

        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE user SET username=?, password=? where id=?");
            insertStatement.setString(1, employee.getUsername());
            insertStatement.setString(2, employee.getPassword());
            insertStatement.setString(3, id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("DELETE from user where id=?");
            insertStatement.setString(1, id);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        return new EmployeeBuilder()
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .setId(rs.getLong("id"))
                .build();
    }


}