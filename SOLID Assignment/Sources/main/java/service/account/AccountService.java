package service.account;

import model.Account;

public interface AccountService {
    String createAccount(String type, String dateOfCreation, String balance);
    boolean updateAccount(String id, String type, String dateOfCreation, String balance);
    boolean deleteAccount(String id);
    Account findByID(String id);
    void viewAccounts();
}
