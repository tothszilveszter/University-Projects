import database.DBConnectionFactory;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import service.ReportService;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.employee.*;

import java.sql.Connection;


public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final ClientService clientService;
    private final EmployeeRepository employeeRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final AccountService accountService;
    private final ReportService reportService;
    private static ComponentFactory instance;


    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.clientRepository= new ClientRepositoryMySQL(connection);
        this.employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.employeeRepository, this.rightsRolesRepository);
        this.clientService = new ClientServiceMySQL(this.clientRepository);
        this.accountService = new AccountServiceMySQL(new AccountRepositoryMySQL(connection));
        this.reportService = new ReportService();
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public ClientService getDutyService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ReportService getReportService() {return reportService;}
}