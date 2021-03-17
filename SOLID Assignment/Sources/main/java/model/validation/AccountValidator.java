package model.validation;

import model.Account;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountValidator {
    private static final String types[] = {"debit","credit"};


    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Account account;

    public AccountValidator(Account account) {
        errors = new ArrayList<>();
        this.account = account;
    }

    public boolean validateAccount(){
        validateType(account.getAccountType());
        validateBalance(account.getBalance());
        validateDate(account.getDateOfCreation());
        return errors.isEmpty();
    }

    private void validateDate(String dateOfCreation) {
        if (dateOfCreation.trim().equals(""))
            errors.add("Date must not be null");

        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        format.setLenient(false);

        try {
            Date javaDate = format.parse(dateOfCreation);
        } catch (ParseException e) {
            errors.add("Date format is invalid");
        }

    }

    private void validateBalance(String balance) {
        if (balance.equals(""))
            errors.add("Balance must not be null");
        if (!containsOnlyDigits(balance))
            errors.add("Balance must contain only digits");
    }

    private void validateType(String accountType) {
        boolean check=false;
        for (String s : types)
        {
            if(accountType.equals(s)) {
                check = true;
                break;
            }
        }
        if (check==false){
            errors.add("Type must be a valid one");
        }
    }

    private boolean containsOnlyDigits(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
