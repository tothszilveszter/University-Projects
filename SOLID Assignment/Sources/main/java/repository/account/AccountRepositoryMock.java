package repository.account;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AccountRepositoryMock implements AccountRepository{

    private List<Account> accounts;

    public AccountRepositoryMock() {
        accounts = new ArrayList<>();
    }

    @Override
    public List<Account> findAll() { return accounts;}

    @Override
    public String findBiggestId() throws EntityNotFoundException {

        Account maxAccount = accounts
                .stream()
                .max(Comparator.comparing(Account::getIdAccount))
                .orElse(null);

        if (!maxAccount.getIdAccount().equals(""))
            return maxAccount.getIdAccount();
        else
            throw new EntityNotFoundException(Long.valueOf(maxAccount.getIdAccount()), Client.class.getSimpleName());

    }

    @Override
    public String create(Account account) {
         accounts.add(account);
         return account.getIdAccount();
    }

    @Override
    public boolean update(String id, Account newAccount) {
        for(Account x : accounts){
            if (x.getIdAccount() == id){
                x.setIdAccount(newAccount.getIdAccount());
                x.setAccountType(newAccount.getAccountType());
                x.setBalance(newAccount.getBalance());
                x.setDateOfCreation(newAccount.getDateOfCreation());
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean delete(String id) {
        Account toDelete=findByID(id);
        return accounts.remove(toDelete);
    }

    @Override
    public Account findByID(String id) {

        for (Account x : accounts){
            if (x.getIdAccount().equals(id))
                return x;
        }
        return null;
    }

    @Override
    public void view() {
        for(Account x : accounts){
            System.out.println(x.getIdAccount()+ " " + x.getAccountType() + " " + x.getBalance() + " " + x.getDateOfCreation());
        }
    }

    @Override
    public void removeAll() {
        accounts.clear();
    }
}
