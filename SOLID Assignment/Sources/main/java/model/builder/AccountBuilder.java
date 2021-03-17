package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {
    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setIdAccount(String id) {
        account.setIdAccount(id);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setAccountType(type);
        return this;
    }

    public AccountBuilder setBalance(String balance) {
        account.setBalance(balance);
        return this;
    }

    public AccountBuilder setDateOfCreation(String date) {
        account.setDateOfCreation(date);
        return this;
    }

    public Account build() {
        return account;
    }
}
