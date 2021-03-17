package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    String findBiggestId() throws EntityNotFoundException;

    Account findByID(String id);

    String create(Account account);

    boolean update(String id, Account newAccount);

    boolean delete(String id);

    void view();

    void removeAll();
}
