package service.account;

import database.DBConnectionFactory;
import junit.framework.TestCase;
import model.Account;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;

import java.sql.Connection;

public class AccountServiceMySQLTest extends TestCase {

    private static AccountService accountService;
    public static final String oldId="2";
    public static final String type = "credit";
    public static final String dateOfCreation = "2020/12/12";
    public static final String balance = "50";
    @BeforeClass
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        AccountRepository accountRepository = new AccountRepositoryMySQL(connection);
        accountService = new AccountServiceMySQL(accountRepository);
    }

    @Test
    public void testCreate() {
        String id = accountService.createAccount(type,dateOfCreation,balance);
        assertTrue(id != null);
    }

    @Test
    public void testUpdate() {
        boolean done = accountService.updateAccount(oldId,type,dateOfCreation,balance);
        assertTrue(done);
    }

    @Test
    public void testFindById() {
        Account account = accountService.findByID(oldId);
        assertTrue(account != null);
    }

    @Test
    public void testDelete() {
        boolean done = accountService.deleteAccount(oldId);
        assertTrue(done);
    }

}