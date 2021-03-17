package service.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.AccountValidator;
import repository.account.AccountRepository;

public class AccountServiceMySQL implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(String type, String dateOfCreation, String balance) {
        Account account = new AccountBuilder()
                .setType(type)
                .setDateOfCreation(dateOfCreation)
                .setBalance(balance)
                .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validateAccount();
        if (accountValid){
            String id=accountRepository.create(account);
            if (id != null){
                return id;
            }
        }
        return null;
    }

    @Override
    public boolean updateAccount(String id, String type, String dateOfCreation, String balance) {
        Account account = new AccountBuilder()
                .setType(type)
                .setDateOfCreation(dateOfCreation)
                .setBalance(balance)
                .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validateAccount();
        if (!accountValid)
            return false;

        if (accountRepository.update(id,account))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String id) {
        if (id.equals(""))
            return false;
        return accountRepository.delete(id);
    }

    @Override
    public void viewAccounts() {
        accountRepository.view();
    }

    @Override
    public Account findByID(String id) {
        if (id.equals(""))
            return null;
        return accountRepository.findByID(id);
    }
}
