package model;

import java.util.Date;

public class Account {
    private String idAccount;
    private String type;
    private String balance;
    private String dateOfCreation;


    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String id) {
        this.idAccount = id;
    }

    public String getAccountType() {
        return type;
    }

    public void setAccountType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
