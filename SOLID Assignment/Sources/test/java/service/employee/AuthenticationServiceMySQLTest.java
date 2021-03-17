package service.employee;

import database.DBConnectionFactory;
import model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;

import java.sql.Connection;

public class AuthenticationServiceMySQLTest {

    public static final String TEST_USERNAME = "test@username.com";
    public static final String TEST_PASSWORD = "TestPassword1@";
    private static AuthenticationService authenticationService;
    private static EmployeeRepository employeeRepository;

    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);

        authenticationService = new AuthenticationServiceMySQL(
                employeeRepository,
                rightsRolesRepository
        );
    }

    @Before
    public void cleanUp() {
        employeeRepository.removeAll();
    }


    @Test
    public void register() throws Exception {
        Assert.assertTrue(
                authenticationService.register(TEST_USERNAME, TEST_PASSWORD).getResult()
        );
    }

    @Test
    public void login() throws Exception {
        authenticationService.register(TEST_USERNAME, TEST_PASSWORD).getResult();
        Employee employee = authenticationService.login(TEST_USERNAME, TEST_PASSWORD).getResult();
        Assert.assertNotNull(employee);
    }


}